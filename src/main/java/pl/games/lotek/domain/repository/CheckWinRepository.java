package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.CheckWinEntity;

import java.time.Instant;
import java.util.List;

public interface CheckWinRepository extends MongoRepository<CheckWinEntity, String> {

    boolean existsByUserIdAndUserNumbersId(String userId, String userNumbersId);
    List<CheckWinEntity> findByUserIdAndDateBetween(String userId, Instant start, Instant end);
    List<CheckWinEntity> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}