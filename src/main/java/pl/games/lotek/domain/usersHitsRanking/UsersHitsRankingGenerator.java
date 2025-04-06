package pl.games.lotek.domain.usersHitsRanking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.util.TimeService;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
class UsersHitsRankingGenerator {

    private final ResultCheckerFacade resultCheckerFacade;
    private final UserBestHitsCalculator userBestHitsCalculator;
    private final RankingEntriesSaver rankingEntriesSaver;
    private final AllTicketsChecker allTicketsChecker;


    void generateRankingForPreviousDay() {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        allTicketsChecker.checkAllUsersTickets(startOfPreviousDay, endOfPreviousDay);
        List<UserResultsDto> previousDayResults = resultCheckerFacade.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        Map<String, Integer> userBestHits = userBestHitsCalculator.calculateUserBestHits(previousDayResults);
        rankingEntriesSaver.saveNewRankingEntries(userBestHits, startOfPreviousDay, endOfPreviousDay);
    }
}
