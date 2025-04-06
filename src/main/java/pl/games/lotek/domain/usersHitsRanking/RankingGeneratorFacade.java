package pl.games.lotek.domain.usersHitsRanking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.usersHitsRanking.dto.UsersHitsRankingDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingGeneratorFacade {

    private final UsersHitsRankingGenerator usersHitsRankingGenerator;
    private final UsersHitsRankingRetriever usersHitsRankingRetriever;

    public void generateRanking(){
        usersHitsRankingGenerator.generateRankingForPreviousDay();
    }

    public List<UsersHitsRankingDto> fetchAllRankingEntries(){
        return usersHitsRankingRetriever.fetchAllRankingEntries();
    }

    public List<UsersHitsRankingDto> fetchAllRankingEntriesByDay(final Long days) {
        return usersHitsRankingRetriever.fetchAllRankingEntriesByDay(days);
    }
}
