package pl.games.lotek.infrastructure.usersHitsRanking.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.usersHitsRanking.RankingGeneratorFacade;

@Service
@AllArgsConstructor
@Log4j2
class RankingGeneratorScheduler {

    private final RankingGeneratorFacade rankingGeneratorFacade;

    @Scheduled(cron = "*/15 * * * * *")
    public void generateRanking() {
        log.info("Generating ranking for previous day");
        rankingGeneratorFacade.generateRanking();
    }
}
