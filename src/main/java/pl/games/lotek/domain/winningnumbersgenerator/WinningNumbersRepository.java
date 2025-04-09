package pl.games.lotek.domain.winningnumbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;

interface WinningNumbersRepository extends MongoRepository<WinningNumbers, String> {

    WinningNumbers findByDateBetween(Instant fromTime, Instant toTime);

    WinningNumbers save(WinningNumbers winningNumbers);
}
