package pl.games.lotek.domain.rankinggenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.rankinggenerator.dto.UsersHitsRankingDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingGeneratorFacade {

    private final UsersHitsRankingGenerator usersHitsRankingGenerator;

    public List<UsersHitsRankingDto> generateRanking(){
        return usersHitsRankingGenerator.generateRanking();
    }
}
