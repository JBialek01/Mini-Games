package pl.games.lotek.infrastructure.resultchecker.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
@RequestMapping("/results")
public class ResultCheckerController {

    ResultCheckerFacade resultCheckerFacade;

    @GetMapping
    public ResponseEntity<List<UserResultsDto>> checkWinResultsForPreviousDay(@AuthenticationPrincipal OAuth2User user) {
        List<UserResultsDto> winsDtos = resultCheckerFacade.getResultsForPreviousDay(user);
        return ResponseEntity.ok(winsDtos);
    }

    @GetMapping("/{daysToSubstract}")
    public ResponseEntity<List<UserResultsDto>> checkWinResultsForSpecifiedDay(@AuthenticationPrincipal OAuth2User user, @PathVariable("daysToSubstract") Long days) {
        List<UserResultsDto> winsDtos = resultCheckerFacade.getResultsForSpecifiedDay(user, days);
        return ResponseEntity.ok(winsDtos);
    }
}
