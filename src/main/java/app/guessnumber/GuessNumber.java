package app.guessnumber;

import app.Game;
import app.Nameable;

public class GuessNumber implements Game, Nameable {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    private final WinningNumberProvider winningNumberProvider;
    private final WinChecker winChecker;
    static int winningNumber;
    String message;

    public GuessNumber(WinningNumberProvider winningNumberProvider, WinChecker winChecker) {
        this.winningNumberProvider = winningNumberProvider;
        this.winChecker = winChecker;
        winningNumber = winningNumberProvider.returnWinningNumber();

    }

    @Override
    public String startGame() {
        message = winChecker.checkWin(winningNumber);
        return message;
    }

    @Override
    public String getName() {
        return "Guess Number";
    }
}
