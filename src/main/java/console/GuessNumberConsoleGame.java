package console;

import app.Game;
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
        this.winChecker = new WinChecker(userNumberProvider);
        this.winningNumberProvider = new WinningNumberProvider();
        this.winningNumber = winningNumberProvider.returnWinningNumber();
    }
    @Override
    public String startGame(boolean isHtml) {
        String message;
        do {
            int userNumber = userNumberProvider.returnUserNumber();
            if (userNumber == -1) {
                message = "Wyszedłeś z gry";
                System.out.println(message);
                return message;
            }
            message = winChecker.checkWin(winningNumber, userNumber);
        } while (!"Gratulacje zgadłeś!".equals(message));

        return "Koniec gry – " + message;
    }
    @Override
    public String getName() {
        return "GuessNumber";
    }
}
