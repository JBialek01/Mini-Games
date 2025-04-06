package pl.games.lotek.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultCheckerFacade {

    private final UserResultsRetriever userResultsRetriever;
    private final UserResultsChecker userResultsChecker;
    private final AuthenticatedUserService authenticatedUserService;

    public List<UserResultsDto> getResultsForPreviousDay(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return userResultsRetriever.getResults(userId);
    }

    public List<UserResultsDto> getResultsForSpecifiedDay(final OAuth2User user, final Long days) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return userResultsRetriever.getResultsForSpecifiedDay(userId, days);
    }

    public void checkAndSaveResults(String userId) {
        userResultsChecker.checkAndSaveResults(userId);
    }

    public List<UserResultsDto> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay) {
        return userResultsRetriever.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
    }
}
