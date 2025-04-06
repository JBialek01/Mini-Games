package pl.games.lotek.domain.usersHitsRanking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.ticketsreceiver.TicketsReceiverFacade;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AllTicketsChecker {

    private final TicketsReceiverFacade ticketsReceiverFacade;
    private final ResultCheckerFacade resultCheckerFacade;

    void checkAllUsersTickets(Instant start, Instant end) {
        List<LotekTicketDto> userTicketsDtos = ticketsReceiverFacade.findByDateBetween(start, end);
        Set<String> userIds = userTicketsDtos.stream().map(LotekTicketDto::userId).collect(Collectors.toSet());
        userIds.forEach(resultCheckerFacade::checkAndSaveResults);
    }
}
