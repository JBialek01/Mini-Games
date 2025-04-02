package pl.games.lotek.infrastructure.rankinggenerator.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.rankinggenerator.RankingGeneratorFacade;
import pl.games.lotek.domain.rankinggenerator.dto.UsersHitsRankingDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
public class RankingGeneratorController {

    RankingGeneratorFacade rankingGeneratorFacade;

    @GetMapping("/ranking")
    public ResponseEntity<List<UsersHitsRankingDto>> getRanking() {
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.generateRanking();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }
}
