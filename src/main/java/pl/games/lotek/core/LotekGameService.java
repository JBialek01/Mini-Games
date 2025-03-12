package pl.games.lotek.core;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.repository.*;
import pl.games.lotek.web.LotekUserNumberWebProvider;
import pl.games.lotek.web.error.UserGaveNumberOutsideTheRange;

import java.time.LocalDate;
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
    private final RankingService rankingService;


    public TicketSubmissionDto submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
        for (Integer number : userNumbers) {
            if (number < 1 || number > 99) {
                throw new UserGaveNumberOutsideTheRange("Podane liczby muszą być w zakresie 1-99!");
            }
        }
        LotekTicketEntity lotekTicketEntity = new LotekTicketEntity(userId, userNumbers.toString(), LocalDate.now());
        lotekRepository.save(lotekTicketEntity);
        return new TicketSubmissionDto(userNumbers, "Los zapisany!");
    }

    public List<CheckWinEntity> checkWin(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        lotekWinningNumbersService.getWinningNumbersForYesterday();
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

    public ResponseEntity<List<RankingEntity>> getRanking() {
        List<RankingEntity> ranking = rankingService.generateRanking();
        return ResponseEntity.ok(ranking);
    }
}
