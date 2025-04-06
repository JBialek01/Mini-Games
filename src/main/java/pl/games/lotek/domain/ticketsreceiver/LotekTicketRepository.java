package pl.games.lotek.domain.ticketsreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

interface LotekTicketRepository extends MongoRepository<LotekTicket, String> {

    LotekTicket save(LotekTicket lotekTicket);

    List<LotekTicket> findAll();

    List<LotekTicket> findByUserIdAndDateBetween(String userId, Instant startOfPreviousDay, Instant endOfPreviousDay);

    List<LotekTicket> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}
