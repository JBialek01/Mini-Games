package pl.games.lotek.domain.ticketsreceiver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.ticketsreceiver.dto.TicketSubmissionDto;
import pl.games.lotek.domain.util.LotekUserNumbersWebProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
class TicketSubmitter {

    private final LotekTicketRepository lotekTicketRepository;
    private final LotekUserNumbersWebProvider userNumbersProvider;
    private final NumbersValidator numbersValidator;

    TicketSubmissionDto submitTicket(String userId) {
        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
        numbersValidator.validateNumbers(userNumbers);
        LotekTicket lotekTicket = new LotekTicket(userId, userNumbers, ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
        lotekTicketRepository.save(lotekTicket);
        return new TicketSubmissionDto(userNumbers, "Ticket saved!");
    }
}
