package pl.games.lotek.domain.ticketsreceiver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
class TicketsRetriever {

    private final LotekTicketRepository lotekTicketRepository;

    List<LotekTicketDto> findByDateBetween(Instant from, Instant to) {
        List<LotekTicket> tickets = lotekTicketRepository.findByDateBetween(from, to);
        return LotekTicketMapper.mapToLotekTicketDto(tickets);
    }

    List<LotekTicketDto> findByUserIdAndDateBetween(final String userId, final Instant startOfPreviousDay, final Instant endOfPreviousDay) {
        List<LotekTicket> tickets = lotekTicketRepository.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);
        return LotekTicketMapper.mapToLotekTicketDto(tickets);
    }
}
