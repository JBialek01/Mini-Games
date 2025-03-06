package pl.games.lotek.web;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import pl.games.app.core.GameResult;
import pl.games.app.core.UserNumbersProvider;
import pl.games.lotek.core.CheckWinService;
import pl.games.lotek.core.TicketSubmission;
import pl.games.lotek.repository.CheckWinEntity;
import pl.games.lotek.repository.CheckWinRepository;
import pl.games.lotek.repository.LotekRepository;
import pl.games.lotek.repository.LotekTicketEntity;
import pl.games.lotek.core.LotekGame;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@Scope("request")
@AllArgsConstructor
public class LotekGameController {

    private final LotekGame lotekGame;
    private final LotekUserNumberWebProvider userNumbersProvider;
//    private final AuthenticatedUserService authenticatedUserService;
    private final LotekRepository lotekRepository;
    private final CheckWinService checkWinService;
    private final CheckWinRepository checkWinRepository;



    @GetMapping("/lotekSubmitTicket")
    public TicketSubmission requestNumbers(@RequestParam("number1") Integer number1,
                                           @RequestParam("number2") Integer number2,
                                           @RequestParam("number3") Integer number3,
                                           @RequestParam("number4") Integer number4,
                                           @RequestParam("number5") Integer number5,
                                           @RequestParam("number6") Integer number6) {

//        lotekGame.getUserNumbersProvider().addNumber(number1);
//        lotekGame.getUserNumbersProvider().addNumber(number2);
//        lotekGame.getUserNumbersProvider().addNumber(number3);
//        lotekGame.getUserNumbersProvider().addNumber(number4);
//        lotekGame.getUserNumbersProvider().addNumber(number5);
//        lotekGame.getUserNumbersProvider().addNumber(number6);
        userNumbersProvider.addNumbers(Set.of(number1, number2, number3, number4, number5, number6));
        TicketSubmission result = lotekGame.submitTicket("2");
        return result;
    }

//    @GetMapping("/submit")
//    public ResponseEntity<String> submitTicket(@AuthenticationPrincipal OAuth2User user, @RequestBody Set<Integer> numbers) {
//        String userId = authenticatedUserService.getAuthenticatedUserId(user);
//        if (userId == null) {
//            return ResponseEntity.status(401).body("Nie jesteś zalogowany.");
//        }
//
//        LotekTicketEntity ticket = new LotekTicketEntity(userId, numbers.toString(), LocalDate.now());
//        lotekRepository.save(ticket);
//        return ResponseEntity.ok("Zakład został zapisany dla użytkownika: " + userId);
//    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<List<LotekTicketEntity>> fetchGameHistoryForAllUsers(){
        return ResponseEntity.ok(lotekGame.fetchGameHistoryForAllUsers());
    }

    @GetMapping("/results")
    public ResponseEntity<List<LotekTicketEntity>> getResultsFromPreviousDay(@RequestParam("userId") String userId) {
        LocalDate previousDay = LocalDate.now().minusDays(1);
        List<LotekTicketEntity> userTickets = lotekRepository.findByUserIdAndDate(userId, previousDay);
        return ResponseEntity.ok(userTickets);
    }

    @GetMapping("/checkWin")
    public List<CheckWinEntity> checkWinResults() {
        String userId = "1";
        checkWinService.checkAndSaveResults(userId);
        return checkWinRepository.findByUserIdAndDate(userId, LocalDate.now().minusDays(1));
    }
}
