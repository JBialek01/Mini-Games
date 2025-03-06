package pl.games.lotek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface WinningNumberRepository extends MongoRepository<WinningNumberEntity, String> {
    WinningNumberEntity findByDate(LocalDate date);
}
