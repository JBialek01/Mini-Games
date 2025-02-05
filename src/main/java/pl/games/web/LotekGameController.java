package pl.games.web;

import pl.games.app.GameResult;
import pl.games.app.lotek.LotekEntity;
import pl.games.app.lotek.LotekGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
class LotekGameController {

    private final LotekGame lotekGame;

    public LotekGameController(LotekGame lotekGame) {
        this.lotekGame = lotekGame;
    }

    @GetMapping("/lotekGame")
    public String requestNumbers(@RequestParam("number1") int number1,
                                 @RequestParam("number2") int number2,
                                 @RequestParam("number3") int number3,
                                 @RequestParam("number4") int number4,
                                 @RequestParam("number5") int number5,
                                 @RequestParam("number6") int number6) {

//        LotekUserNumberWebProvider userNumbersProvider =
//                new LotekUserNumberWebProvider(number1, number2, number3, number4, number5, number6);

        //LotekRandomNumbersProvider randomNumbersProvider = new LotekRandomNumbersProvider();
//        LotekGame lotekGame = new LotekGame(userNumbersProvider, randomNumbersProvider);

        lotekGame.getUserNumbersProvider().addNumber(number1);
        lotekGame.getUserNumbersProvider().addNumber(number2);
        lotekGame.getUserNumbersProvider().addNumber(number3);
        lotekGame.getUserNumbersProvider().addNumber(number4);
        lotekGame.getUserNumbersProvider().addNumber(number5);
        lotekGame.getUserNumbersProvider().addNumber(number6);
        GameResult result = lotekGame.startGame();


        String html = showResultHtml(result.getUserNumbers(), result.getWinningNumbers(), result.getMessage());
        return html;
    }

//    @GetMapping("/lotekGame2")
//    public ResponseEntity<?> requestNumbersWithJson(@RequestParam("number1") int number1,
//                                                    @RequestParam("number2") int number2,
//                                                    @RequestParam("number3") int number3,
//                                                    @RequestParam("number4") int number4,
//                                                    @RequestParam("number5") int number5,
//                                                    @RequestParam("number6") int number6) {
//        try {
//            LotekUserNumberWebProvider userNumbersProvider =
//                    new LotekUserNumberWebProvider(number1, number2, number3, number4, number5, number6);
//            LotekRandomNumbersProvider randomNumbersProvider = new LotekRandomNumbersProvider();
//            LotekGame lotekGame = new LotekGame(userNumbersProvider, randomNumbersProvider);
//
//            GameResult result = lotekGame.startGame();
//
//            Set<Integer> userNumbers = result.getUserNumbers();
//            Set<Integer> winningNumbers = result.getWinningNumbers();
//            String message = result.getMessage();
////        String html = showResultHtml(userNumbers, winningNumbers, message);
//
//            LotekGameResponseJson lotekGameResponseJson = new LotekGameResponseJson(userNumbers, winningNumbers, message);
//            ResponseEntity<LotekGameResponseJson> ok = ResponseEntity.ok(lotekGameResponseJson);
//            return ok;
//        } catch (UserGaveDifferentNumberCountThanSix e) {
//            GameResponseErrorJson gameResponseErrorJson = new GameResponseErrorJson(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gameResponseErrorJson);
//        }
//    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<Map<String, LotekEntity>> fetchGameHistoryForAllUsers(){
        return ResponseEntity.ok(lotekGame.fetchGameHistoryForAllUsers());
    }

    private String showResultHtml(Set<Integer> userNumbers, Set<Integer> winningNumbers, String checkResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Twoje liczby:</h2><p>");
        for (int userNumber : userNumbers) {
            sb.append("[").append(userNumber).append("] ");
        }
        sb.append("</p>");
        sb.append("<h2>Zwycięskie liczby:</h2><p>");
        for (int winningNumber : winningNumbers) {
            sb.append("[").append(winningNumber).append("] ");
        }
        sb.append("</p>");
        checkResult = checkResult.replace("\n", "<br>");
        sb.append("<p>").append(checkResult).append("</p>");
        return sb.toString();
    }

}
