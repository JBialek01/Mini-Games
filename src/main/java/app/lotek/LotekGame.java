package app.lotek;

import app.Game;

import java.util.Set;
import java.util.TreeSet;

public class LotekGame implements Game {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 99;
    public static final int NUMBERS_COUNT = 6;
    private final LotekUserNumbersProvider userNumbersProvider;
    private final LotekRandomNumbersProvider randomNumbersProvider;
    private final LotekResultShower resultShower = new LotekResultShower();
    Set<Integer> userNumbers = new TreeSet<>();
    Set<Integer> winningNumbers = new TreeSet<>();
    String message;

    public LotekGame(LotekUserNumbersProvider userNumbersProvider, LotekRandomNumbersProvider randomNumbersProvider) {
        this.userNumbersProvider = userNumbersProvider;
        this.randomNumbersProvider = randomNumbersProvider;
    }

    @Override
    public void startGame() {
        userNumbers = userNumbersProvider.returnUserNumbers();
        winningNumbers = randomNumbersProvider.returnWinningNumbers();
        message = resultShower.showResult(userNumbers, winningNumbers);
    }
}
