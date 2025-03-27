package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.LotekConstants;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.repository.LotekTicketRepository;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.controller.error.UserGaveNumberOutsideTheRange;

import java.time.*;
import java.util.Set;

@Service
@AllArgsConstructor
public class SubmitTicketService {

    private final LotekTicketRepository lotekTicketRepository;
    private final LotekUserNumberWebProvider userNumbersProvider;

    public TicketSubmissionDto submitTicket(String userId) {
        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
        for (Integer number : userNumbers) {
            if (number < LotekConstants.LOWEST_NUMBER || number > LotekConstants.HIGHEST_NUMBER) {
                throw new UserGaveNumberOutsideTheRange("Podane liczby muszą być w zakresie 1-99!");
            }
        }
        LotekTicketEntity lotekTicketEntity = new LotekTicketEntity(userId, userNumbers, ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
        lotekTicketRepository.save(lotekTicketEntity);
        return new TicketSubmissionDto(userNumbers, "Los zapisany!");
    }
}
