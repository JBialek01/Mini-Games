package pl.games.lotek.domain.usershitsranking;

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


    String generateRankingForPreviousDay() {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        allTicketsChecker.checkAllUsersTickets(startOfPreviousDay, endOfPreviousDay);
        List<UserResultsDto> previousDayResults = resultCheckerFacade.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        Map<String, Integer> userBestHits = userBestHitsCalculator.calculateUserBestHits(previousDayResults);
        rankingEntriesSaver.saveNewRankingEntries(userBestHits, startOfPreviousDay, endOfPreviousDay, 1L);
        return "Ranking for previous day generated successfully";
    }

    String generateRankingForSpecifiedDay(final Long days) {
        Instant startOfSpecifiedDay = TimeService.getStartOfSpecifiedDay(days);
        Instant endOfSpecifiedDay = TimeService.getEndOfSpecifiedDay(days);
        allTicketsChecker.checkAllUsersTickets(startOfSpecifiedDay, endOfSpecifiedDay);
        List<UserResultsDto> specifiedDayResults = resultCheckerFacade.findByDateBetween(startOfSpecifiedDay, endOfSpecifiedDay);
        Map<String, Integer> userBestHits = userBestHitsCalculator.calculateUserBestHits(specifiedDayResults);
        rankingEntriesSaver.saveNewRankingEntries(userBestHits, startOfSpecifiedDay, endOfSpecifiedDay, days);
        return "Ranking for specified day generated successfully";
    }
}
