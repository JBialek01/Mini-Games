package pl.games.lotek.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import pl.games.app.core.GameResult;
import pl.games.app.core.UserNumbersProvider;
import pl.games.lotek.repository.LotekEntity;
import pl.games.lotek.core.LotekGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@Scope("request")
public class LotekGameController {

    private final LotekGame lotekGame;
    private final UserNumbersProvider userNumbersProvider;

    public LotekGameController(LotekGame lotekGame, LotekUserNumberWebProvider userNumbersProvider) {
        this.lotekGame = lotekGame;
        this.userNumbersProvider = userNumbersProvider;
    }

    @GetMapping("/lotekGame")
    public GameResult requestNumbers(@RequestParam("number1") Integer number1,
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
        GameResult result = lotekGame.startGame();
        return result;
    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<List<LotekEntity>> fetchGameHistoryForAllUsers(){
        return ResponseEntity.ok(lotekGame.fetchGameHistoryForAllUsers());
    }
}
