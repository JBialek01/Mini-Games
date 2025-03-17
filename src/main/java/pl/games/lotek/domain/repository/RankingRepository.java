package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.RankingEntity;

import java.time.LocalDate;
import java.util.List;

public interface RankingRepository extends MongoRepository<RankingEntity, String> {
    List<RankingEntity> findByDate(LocalDate date);
    List<RankingEntity> findByDateAndUserId(LocalDate previousDay, String key);
}
