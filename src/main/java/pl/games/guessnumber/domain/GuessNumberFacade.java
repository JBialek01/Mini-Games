package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;
import pl.games.guessnumber.domain.dto.SavedNumberDto;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;

@Service
@AllArgsConstructor
public class GuessNumberFacade {

    private final AuthenticatedUserService authenticatedUserService;
    private final UserResultRetriever userResultRetriever;
    private final NumberSaver numberSaver;
    private final ResultChecker resultChecker;

    public NumberSubmissionDto submitNumber(OAuth2User user, final NumberRequestDto requestDto) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        SavedNumberDto savedNumberDto = numberSaver.saveNumber(userId, requestDto);
        return resultChecker.checkResult(userId, savedNumberDto);
    }

    public NumberSubmissionDto getResultForSpecifiedDay(OAuth2User user, final Integer days) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return userResultRetriever.getResultForSpecifiedUserAndDay(userId, days);
    }
}
