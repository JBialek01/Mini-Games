package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.WinningNumberEntity;

import java.time.LocalDate;

public interface WinningNumberRepository extends MongoRepository<WinningNumberEntity, String> {
    WinningNumberEntity findByDate(LocalDate date);
}
