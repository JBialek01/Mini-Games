package pl.games.guessnumber.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;

interface WinningNumberRepository extends MongoRepository<WinningNumber, String> {
    WinningNumber save(WinningNumber entity);
    WinningNumber findByDateBetween(Instant fromTime, Instant toTime);
}
