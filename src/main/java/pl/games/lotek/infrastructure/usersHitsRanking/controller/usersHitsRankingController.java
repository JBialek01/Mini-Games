package pl.games.lotek.infrastructure.usersHitsRanking.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.usersHitsRanking.RankingGeneratorFacade;
import pl.games.lotek.domain.usersHitsRanking.dto.UsersHitsRankingDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
@RequestMapping("/ranking")
public class usersHitsRankingController {

    RankingGeneratorFacade rankingGeneratorFacade;

    @PostMapping("/generate")
    public ResponseEntity<List<UsersHitsRankingDto>> generateAndGetRankingForPreviousDay() {
        rankingGeneratorFacade.generateRanking();
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.fetchAllRankingEntries();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }

    @GetMapping
    public ResponseEntity<List<UsersHitsRankingDto>> getRankingForPreviousDay() {
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.fetchAllRankingEntries();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }

    @GetMapping("/{daysToSubstract}")
    public ResponseEntity<List<UsersHitsRankingDto>> getRankingForSpecifiedDay(@PathVariable("daysToSubstract") Long days) {
        List<UsersHitsRankingDto> usersHitsRankingDtos = rankingGeneratorFacade.fetchAllRankingEntriesByDay(days);
        return ResponseEntity.ok(usersHitsRankingDtos);
    }
}
