package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.model.LotekConstants;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.repository.LotekRepository;
import pl.games.lotek.infrastructure.controller.LotekUserNumberWebProvider;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.controller.error.UserGaveNumberOutsideTheRange;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor
public class SubmitTicketService {

    private final AuthenticatedUserService authenticatedUserService;
    private final LotekRepository lotekRepository;
    private final LotekUserNumberWebProvider userNumbersProvider;

    public TicketSubmissionDto submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
        for (Integer number : userNumbers) {
            if (number < LotekConstants.LOWEST_NUMBER || number > LotekConstants.HIGHEST_NUMBER) {
                throw new UserGaveNumberOutsideTheRange("Podane liczby muszą być w zakresie 1-99!");
            }
        }
        LotekTicketEntity lotekTicketEntity = new LotekTicketEntity(userId, userNumbers, LocalDate.now());
        lotekRepository.save(lotekTicketEntity);
        return new TicketSubmissionDto(userNumbers, "Los zapisany!");
    }
}
