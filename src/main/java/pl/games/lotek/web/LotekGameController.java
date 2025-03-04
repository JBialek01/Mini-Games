package pl.games.lotek.web;

import org.springframework.context.annotation.Scope;
import pl.games.app.core.GameResult;
import pl.games.lotek.repository.LotekEntity;
import pl.games.lotek.core.LotekGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("request")
public class LotekGameController {

    private final LotekGame lotekGame;

    public LotekGameController(LotekGame lotekGame) {
        this.lotekGame = lotekGame;
    }

    @GetMapping("/lotekGame")
    public GameResult requestNumbers(@RequestParam("number1") Integer number1,
                                     @RequestParam("number2") Integer number2,
                                     @RequestParam("number3") Integer number3,
                                     @RequestParam("number4") Integer number4,
                                     @RequestParam("number5") Integer number5,
                                     @RequestParam("number6") Integer number6) {

        lotekGame.getUserNumbersProvider().addNumber(number1);
        lotekGame.getUserNumbersProvider().addNumber(number2);
        lotekGame.getUserNumbersProvider().addNumber(number3);
        lotekGame.getUserNumbersProvider().addNumber(number4);
        lotekGame.getUserNumbersProvider().addNumber(number5);
        lotekGame.getUserNumbersProvider().addNumber(number6);
        GameResult result = lotekGame.startGame();
        return result;
    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<List<LotekEntity>> fetchGameHistoryForAllUsers(){
        return ResponseEntity.ok(lotekGame.fetchGameHistoryForAllUsers());
    }
}
