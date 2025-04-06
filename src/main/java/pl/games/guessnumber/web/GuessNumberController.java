package pl.games.guessnumber.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.games.app.core.UserNumbersProvider;
import pl.games.guessnumber.core.GuessNumber;

import java.util.Set;

@RestController
class GuessNumberController {

    private final GuessNumber guessNumber;
    private final UserNumbersProvider userNumbersProvider;


    public GuessNumberController(GuessNumber guessNumber, GuessNumberUserNumberWebProvider userNumbersProvider) {
        this.guessNumber = guessNumber;
        this.userNumbersProvider = userNumbersProvider;
    }

    @GetMapping("/guessNumber")
    public String requestNumber(@RequestParam("number") int number) {
//        WinChecker winChecker = new WinChecker(new UserNumberWebProvider(number));
        //WinChecker winChecker = new WinChecker();
        //return winChecker.checkWin(guessNumberSession.getWinningNumber(), number);
        userNumbersProvider.addNumbers(Set.of(number));
        return guessNumber.startGame().getMessage();
    }
}
