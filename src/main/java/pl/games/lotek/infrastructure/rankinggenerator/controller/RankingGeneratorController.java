package pl.games.lotek.infrastructure.rankinggenerator.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.rankinggenerator.RankingGeneratorFacade;
import pl.games.lotek.domain.rankinggenerator.dto.UsersHitsRankingDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
@RequestMapping("/ranking")
public class RankingGeneratorController {

    RankingGeneratorFacade rankingGeneratorFacade;

    @PostMapping("/generate")
    public ResponseEntity<List<UsersHitsRankingDto>> generateAndGetRanking() {
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.generateRanking();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }

    @GetMapping
    public ResponseEntity<List<UsersHitsRankingDto>> getRanking() {
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.fetchAllRankingEntires();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }
}
