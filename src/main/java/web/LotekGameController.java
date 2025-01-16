package web;

import app.GameResult;
import app.lotek.LotekGame;
import app.lotek.LotekRandomNumbersProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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

        GameResult result = lotekGame.startGame();

        String html = showResultHtml(result.getUserNumbers(), result.getWinningNumbers(), result.getMessage());
        return html;
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
