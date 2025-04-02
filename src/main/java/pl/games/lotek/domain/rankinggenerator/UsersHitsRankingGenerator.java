package pl.games.lotek.domain.rankinggenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.rankinggenerator.dto.UsersHitsRankingDto;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.util.TimeService;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UsersHitsRankingGenerator {

    private final UsersHitsRankingRepository usersHitsRankingRepository;
    private final ResultCheckerFacade resultCheckerFacade;
    private final UserBestHitsCalculator userBestHitsCalculator;
    private final RankingEntriesSaver rankingEntriesSaver;
    private final AllTicketsChecker allTicketsChecker;


    List<UsersHitsRankingDto> generateRanking() {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        allTicketsChecker.checkAllUsersTickets(startOfPreviousDay, endOfPreviousDay);
        List<UserResultsDto> previousDayResults = resultCheckerFacade.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        Map<String, Integer> userBestHits = userBestHitsCalculator.calculateUserBestHits(previousDayResults);
        rankingEntriesSaver.saveNewRankingEntries(userBestHits, startOfPreviousDay, endOfPreviousDay);
        List<UsersHitsRanking> ranking = fetchSortedRanking(startOfPreviousDay, endOfPreviousDay);
        return UsersHitsRankingMapper.mapToRankingDto(ranking);
    }

    private List<UsersHitsRanking> fetchSortedRanking(Instant start, Instant end) {
        return usersHitsRankingRepository.findByDateBetween(start, end).stream()
                .sorted(Comparator.comparing(UsersHitsRanking::getHits).reversed())
                .collect(Collectors.toList());
    }
}
