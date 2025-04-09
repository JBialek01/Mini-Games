package pl.games.lotek.domain.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.games.auth.AuthenticatedUserService;

@Configuration
class ResultCheckerFacadeConfiguration {

    @Bean
    ResultCheckerFacade resultCheckerFacade(UserResultsRetriever userResultsRetriever, UserResultsChecker userResultsChecker, AuthenticatedUserService authenticatedUserService) {
        return new ResultCheckerFacade(userResultsRetriever, userResultsChecker, authenticatedUserService);
    }
}
