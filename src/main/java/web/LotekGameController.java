package web;

import app.Game;
import app.guessnumber.GuessNumber;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;
import app.lotek.LotekGame;
import app.lotek.LotekRandomNumbersProvider;
import app.lotek.LotekUserNumbersProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotekGameController {

    @GetMapping("/lotekGame")
    public String requestNumbers(@RequestParam("number1") int number1,
                                 @RequestParam("number2") int number2,
                                 @RequestParam("number3") int number3,
                                 @RequestParam("number4") int number4,
                                 @RequestParam("number5") int number5,
                                 @RequestParam("number6") int number6) {

        LotekUserNumberWebProvider userNumbersProvider =
                new LotekUserNumberWebProvider(number1, number2, number3, number4, number5, number6);

        LotekRandomNumbersProvider randomNumbersProvider = new LotekRandomNumbersProvider();

        LotekGame lotekGame = new LotekGame(userNumbersProvider, randomNumbersProvider);

        String result = lotekGame.startGame(true);

        return result;
    }


}
