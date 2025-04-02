package pl.games.lotek.domain.ticketsreceiver;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.ticketsreceiver.dto.TicketSubmissionDto;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketsReceiverFacade {

    private final TicketSubmitter ticketSubmitter;
    private final AuthenticatedUserService authenticatedUserService;
    private final TicketsRetriever ticketsRetriever;

    public TicketSubmissionDto submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return ticketSubmitter.submitTicket(userId);
    }

    public List<LotekTicketDto> findByDateBetween (Instant start, Instant end) {
        return ticketsRetriever.findByDateBetween(start, end);
    }

    public List<LotekTicketDto> findByUserIdAndDateBetween (String userId, Instant startOfPreviousDay, Instant endOfPreviousDay){
        return ticketsRetriever.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);
    }
}
