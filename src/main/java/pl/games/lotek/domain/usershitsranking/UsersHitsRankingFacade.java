package pl.games.lotek.domain.usershitsranking;

import lombok.AllArgsConstructor;
import pl.games.lotek.domain.usershitsranking.dto.UsersHitsRankingDto;

import java.util.List;


@AllArgsConstructor
public class UsersHitsRankingFacade {

    private final UsersHitsRankingGenerator usersHitsRankingGenerator;
    private final UsersHitsRankingRetriever usersHitsRankingRetriever;

    public String generateRankingForYesterday() {
        return usersHitsRankingGenerator.generateRankingForPreviousDay();
    }

    public String generateRankingForSpecifiedDay(final Long days) {
        return usersHitsRankingGenerator.generateRankingForSpecifiedDay(days);
    }

    public List<UsersHitsRankingDto> fetchAllRankingEntries() {
        return usersHitsRankingRetriever.fetchAllRankingEntries();
    }

    public List<UsersHitsRankingDto> fetchAllRankingEntriesByDay(final Long days) {
        return usersHitsRankingRetriever.fetchAllRankingEntriesByDay(days);
    }
}
