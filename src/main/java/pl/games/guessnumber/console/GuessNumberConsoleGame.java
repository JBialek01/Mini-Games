package pl.games.guessnumber.console;

import pl.games.app.core.Game;
import pl.games.app.core.GameResult;
import pl.games.guessnumber.core.UserNumberProvider;
import pl.games.guessnumber.core.WinChecker;
import pl.games.guessnumber.core.WinningNumberProvider;

import java.util.Collections;

public class GuessNumberConsoleGame implements Game {

    private final WinChecker winChecker;
    private final WinningNumberProvider winningNumberProvider;
    private final UserNumberProvider userNumberProvider;
    private final int winningNumber;

    public GuessNumberConsoleGame(UserNumberProvider userNumberProvider) {
        this.userNumberProvider = userNumberProvider;
        this.winChecker = new WinChecker();
        this.winningNumberProvider = new WinningNumberProvider();
        this.winningNumber = winningNumberProvider.returnWinningNumber();
    }

    @Override
    public GameResult startGame() {
        return checkWin();
    }

    private GameResult checkWin() {
        String message;
        do {
            int userNumber = userNumberProvider.returnUserNumber();
            if (userNumber == -1) {
                message = "Wyszedłeś z gry";
                System.out.println(message);
                return new GameResult(null, null, message);
            }
            message = winChecker.checkWin(winningNumber, userNumber);
        } while (!"Gratulacje zgadłeś!".equals(message));

        return new GameResult(Collections.emptySet(), Collections.emptySet(), "Koniec gry – " + message);
    }

    @Override
    public String getName() {
        return "GuessNumber";
    }
}
