package pl.games.guessnumber.core;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.games.app.core.Game;
import pl.games.app.core.GameResult;
import pl.games.app.core.Nameable;
import pl.games.app.core.UserNumbersProvider;
import pl.games.guessnumber.web.GuessNumberUserNumberWebProvider;

import java.util.Random;

@Service
@SessionScope
public class GuessNumber implements Game, Nameable {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    private final WinChecker winChecker;
    private final int winningNumber;
    private final UserNumbersProvider userNumbersProvider;
    String message;

    public GuessNumber(WinChecker winChecker, GuessNumberUserNumberWebProvider userNumbersProvider) {
        this.winChecker = winChecker;
        this.userNumbersProvider = userNumbersProvider;
        this.winningNumber = new Random().nextInt(LOWEST_NUMBER, HIGHEST_NUMBER);
    }

    @Override
    public GameResult startGame() {
//        UserNumberProviderInterface userNumberProvider = winChecker.getUserNumberProvider();
        int userNumber = userNumbersProvider.returnUserNumbers().stream().findFirst().get();
        message = winChecker.checkWin(winningNumber, userNumber);
        return new GameResult(null, null, message);
    }

    @Override
    public String getName() {
        return "Guess Number";
    }
}
