package web;

import app.guessnumber.GuessNumber;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;
import app.lotek.LotekGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotekGameController {

    @GetMapping("/lotekGame")
    public String requestNumbers(@RequestParam("number1") int number,
                                 @RequestParam("number2") int number2,
                                 @RequestParam("number3") int number3,
                                 @RequestParam("number4") int number4,
                                 @RequestParam("number5") int number5,
                                 @RequestParam("number6") int number6) {
        LotekGame lotekGame = new LotekGame(new WinningNumberProvider(), new WinChecker(
                new LotekUserNumberWebProvider(number, number2, number3, number4, number5, number6)
        ));
        return lotekGame.startGame();
    }


}
