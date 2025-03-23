package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;

import java.time.Instant;
import java.util.List;

public interface LotekTicketRepository extends MongoRepository<LotekTicketEntity, String> {

    LotekTicketEntity save(LotekTicketEntity lotekTicketEntity);
    List<LotekTicketEntity> findAll();
    List<LotekTicketEntity> findByUserIdAndDateBetween(String userId, Instant startOfPreviousDay, Instant endOfPreviousDay);
    List<LotekTicketEntity> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}
