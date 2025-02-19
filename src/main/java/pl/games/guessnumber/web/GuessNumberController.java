package pl.games.guessnumber.web;

import pl.games.guessnumber.core.WinChecker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GuessNumberController {

    private final GuessNumberSession guessNumberSession;

    public GuessNumberController(GuessNumberSession guessNumberSession) {
        this.guessNumberSession = guessNumberSession;
    }

    @GetMapping("/guessNumber")
    public String requestNumber(@RequestParam("number") int number){
//        WinChecker winChecker = new WinChecker(new UserNumberWebProvider(number));
        WinChecker winChecker = new WinChecker();
        return winChecker.checkWin(guessNumberSession.getWinningNumber(), number);
    }
}
