package pl.games.lotek.infrastructure.resultchecker.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
public class ResultCheckerController {

    ResultCheckerFacade resultCheckerFacade;

    @GetMapping("/checkWin")
    public ResponseEntity<List<UserResultsDto>> checkWinResults(@AuthenticationPrincipal OAuth2User user) {
        List<UserResultsDto> winsDtos = resultCheckerFacade.getResults(user);
        return ResponseEntity.ok(winsDtos);
    }
}
