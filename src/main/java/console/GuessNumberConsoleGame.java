package console;

import app.Game;
import app.GameResult;
import app.guessnumber.UserNumberProviderInterface;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;

public class GuessNumberConsoleGame implements Game {

    private final WinChecker winChecker;
    private final WinningNumberProvider winningNumberProvider;
    private final UserNumberProviderInterface userNumberProvider;
    private final int winningNumber;

    public GuessNumberConsoleGame(UserNumberProviderInterface userNumberProvider) {
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

        return new GameResult(null, null, "Koniec gry – " + message);
    }

    @Override
    public String getName() {
        return "GuessNumber";
    }
}
