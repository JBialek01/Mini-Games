package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.guessnumber.domain.dto.DrawDateDto;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;
import pl.games.guessnumber.domain.dto.SavedNumberDto;
import pl.games.guessnumber.domain.dto.UserRankingDto;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;

import java.util.List;

@Service
@AllArgsConstructor
public class GuessNumberFacade {

    private final AuthenticatedUserService authenticatedUserService;
    private final UserResultRetriever userResultRetriever;
    private final UserNumberSaver userNumberSaver;
    private final ResultChecker resultChecker;
    private final DrawDateRetriever drawDateRetriever;
    private final UserRankingRetriever userRankingRetriever;

    public NumberSubmissionDto submitNumber(OAuth2User user, final NumberRequestDto requestDto) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        SavedNumberDto savedNumberDto = userNumberSaver.saveNumber(userId, requestDto);
        return resultChecker.checkResult(userId, savedNumberDto);
    }

    public NumberSubmissionDto getResultForSpecifiedDay(OAuth2User user, final Integer days) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return userResultRetriever.getResultForSpecifiedUserAndDay(userId, days);
    }

    public DrawDateDto getNextDrawDate() {
        return drawDateRetriever.getNextDrawDate();
    }

    public List<UserRankingDto> getRanking(final Long daysToSubtract) {
        return userRankingRetriever.getRanking(daysToSubtract);
    }
}
