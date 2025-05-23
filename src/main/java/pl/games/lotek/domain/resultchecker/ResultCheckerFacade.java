package pl.games.lotek.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class ResultCheckerFacade {

    private final UserResultsRetriever userResultsRetriever;
    private final UserResultsChecker userResultsChecker;
    private final AuthenticatedUserService authenticatedUserService;

    public List<UserResultsDto> getResultsForSpecifiedDay(final OAuth2User user, final long days) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return userResultsRetriever.getResultsForSpecifiedDay(userId, days);
    }

    public List<UserResultsDto> checkAndSaveResults(String userId) {
        return userResultsChecker.checkAndSaveResults(userId);
    }

    public List<UserResultsDto> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay) {
        return userResultsRetriever.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
    }

    public void saveUserResult(final UserResultsDto testResult) {
        userResultsChecker.saveUserResults(testResult);
    }
}
