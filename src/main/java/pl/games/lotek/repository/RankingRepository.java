package pl.games.lotek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface RankingRepository extends MongoRepository<RankingEntity, String> {
    List<RankingEntity> findByDate(LocalDate date);
    List<RankingEntity> findByDateAndUserId(LocalDate previousDay, String key);
}
