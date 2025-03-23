package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.UserHitsRankingEntity;

import java.time.Instant;
import java.util.List;

public interface UserHitsRankingRepository extends MongoRepository<UserHitsRankingEntity, String> {

    List<UserHitsRankingEntity> findByDateBetweenAndUserId(Instant startOfPreviousDay, Instant endOfPreviousDay, String userId);
    List<UserHitsRankingEntity> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}
