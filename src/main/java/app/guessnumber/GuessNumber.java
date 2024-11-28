package app.guessnumber;

import app.Game;

public class GuessNumber implements Game {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    private final WinningNumberProvider winningNumberProvider;
    private final WinChecker winChecker = new WinChecker();
    int winningNumber;

    public GuessNumber(WinningNumberProvider winningNumberProvider) {
        this.winningNumberProvider = winningNumberProvider;
    }

    @Override
    public void startGame() {
        winningNumber = winningNumberProvider.returnWinningNumber();
        winChecker.checkWin(winningNumber);
    }
}
