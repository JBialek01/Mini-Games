package pl.games.lotek.domain.usershitsranking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersHitsRankingFacadeConfiguration {

    @Bean
    UsersHitsRankingFacade usersHitsRankingFacade(UsersHitsRankingGenerator usersHitsRankingGenerator, UsersHitsRankingRetriever usersHitsRankingRetriever) {
        return new UsersHitsRankingFacade(usersHitsRankingGenerator, usersHitsRankingRetriever);
    }
}
