package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.WinningNumbersEntity;

import java.time.Instant;

public interface WinningNumbersRepository extends MongoRepository<WinningNumbersEntity, String> {

    WinningNumbersEntity findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}
