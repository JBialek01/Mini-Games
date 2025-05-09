package pl.games.guessnumber.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.games.guessnumber.domain.GuessNumberFacade;
import pl.games.guessnumber.domain.dto.DrawDateDto;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;
import pl.games.guessnumber.domain.dto.UserRankingDto;

import java.util.List;

@RestController
@AllArgsConstructor
@Scope("session")
@RequestMapping("/guessNumber")
class GuessNumberController {

    GuessNumberFacade facade;

    @PostMapping
    public ResponseEntity<NumberSubmissionDto> submitNumber(@AuthenticationPrincipal OAuth2User user, @RequestParam("number") Integer number) {
        NumberRequestDto requestDto = new NumberRequestDto(number);
        NumberSubmissionDto response = facade.submitNumber(user, requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{days}")
    public ResponseEntity<NumberSubmissionDto> getResultForSpecifiedDay(@AuthenticationPrincipal OAuth2User user, @PathVariable Integer days) {
        NumberSubmissionDto response = facade.getResultForSpecifiedDay(user, days);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nextDrawDate")
    public ResponseEntity<DrawDateDto> getNextDrawDate() {
        DrawDateDto response = facade.getNextDrawDate();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ranking/{days}")
    public ResponseEntity<List<UserRankingDto>> getRanking(@PathVariable Long days) {
        List<UserRankingDto> response = facade.getRanking(days);
        return ResponseEntity.ok(response);
    }

}
