package pl.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.repository.UserHitsRankingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class Startup implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
     UserHitsRankingRepository repository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("add test data");
        List<UserHitsRankingEntity> userHitsRankingEntities = List.of(
                new UserHitsRankingEntity(LocalDateTime.of(2025, 3, 1, 12, 0, 0,0), 5, "55"),
                new UserHitsRankingEntity(LocalDateTime.now(ZoneId.of("UTC")), 5, "1")
//                new UserHitsRankingEntity(LocalDate.of(2025, 1, 1), 5, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2023, 3, 1), 5, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2022, 1, 1), 4, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2021, 2, 1), 5, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2026, 1, 1), 3, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2028, 1, 1), 5, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2029, 5, 1), 2, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2024, 1, 1), 5, "1"),
//                new UserHitsRankingEntity(LocalDate.of(2022, 7, 1), 5, "1")
        );
        repository.saveAll(userHitsRankingEntities);
    }
}