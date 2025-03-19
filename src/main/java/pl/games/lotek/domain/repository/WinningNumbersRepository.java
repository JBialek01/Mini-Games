package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.WinningNumbersEntity;

import java.time.LocalDate;

public interface WinningNumbersRepository extends MongoRepository<WinningNumbersEntity, String> {
    WinningNumbersEntity findByDate(LocalDate date);
}
