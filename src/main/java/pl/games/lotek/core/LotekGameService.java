package pl.games.lotek.core;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.repository.CheckWinEntity;
import pl.games.lotek.repository.CheckWinRepository;
import pl.games.lotek.repository.LotekRepository;
import pl.games.lotek.repository.LotekTicketEntity;
import pl.games.lotek.web.LotekUserNumberWebProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Component
@Scope("prototype")
@AllArgsConstructor
public class LotekGameService {

    private final LotekUserNumberWebProvider userNumbersProvider;
    private final LotekRepository lotekRepository;
    private final LotekWinningNumbersService lotekWinningNumbersService;
    private final CheckWinService checkWinService;
    private final CheckWinRepository checkWinRepository;
    private final AuthenticatedUserService authenticatedUserService;


    public TicketSubmission submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
        LotekTicketEntity lotekTicketEntity = new LotekTicketEntity(userId, userNumbers.toString(), LocalDate.now());
        lotekRepository.save(lotekTicketEntity);
        return new TicketSubmission(userNumbers, "Los zapisany!");
    }

    public List<CheckWinEntity> checkWin(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        lotekWinningNumbersService.getWinningNumbersForToday();
        List<LotekTicketEntity> userTickets = lotekRepository.findByUserId(userId);
        if (userTickets.isEmpty()) {
            return List.of();
        }

        for (LotekTicketEntity ticket : userTickets) {
            checkWinService.checkAndSaveResults(userId);
        }
        return checkWinRepository.findByUserIdAndDate(userId, LocalDate.now().minusDays(1));
    }

    public ResponseEntity<List<LotekTicketEntity>> showAllTickets() {
        return ResponseEntity.ok(lotekRepository.findAll());
    }
}
