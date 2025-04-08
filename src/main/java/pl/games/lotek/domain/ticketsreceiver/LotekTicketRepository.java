package pl.games.lotek.domain.ticketsreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

interface LotekTicketRepository extends MongoRepository<LotekTicket, String> {

    LotekTicket save(LotekTicket lotekTicket);

    List<LotekTicket> findAll();

    List<LotekTicket> findByUserIdAndDateBetween(String userId, Instant fromTime, Instant toTime);

    List<LotekTicket> findByDateBetween(Instant fromTime, Instant toTime);
}
