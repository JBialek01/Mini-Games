package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.UserHitsRankingEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserHitsRankingRepository extends MongoRepository<UserHitsRankingEntity, String> {
    List<UserHitsRankingEntity> findByDate(LocalDate date);
    List<UserHitsRankingEntity> findByDateAndUserId(LocalDate previousDay, String key);
}
