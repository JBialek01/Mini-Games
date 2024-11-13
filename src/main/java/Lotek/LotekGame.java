package Lotek;

import java.util.Random;
import java.util.Scanner;

public class LotekGame {

    private LotekUserNumbersProvider userNumbersProvider;
    private LotekRandomNumbersProvider randomNumbersProvider;

    public LotekGame(LotekUserNumbersProvider userNumbersProvider, LotekRandomNumbersProvider randomNumbersProvider) {
        this.userNumbersProvider = userNumbersProvider;
        this.randomNumbersProvider = randomNumbersProvider;
    }

    public void lotekGame() {
        LotekResultShower.showResult(userNumbersProvider.insertUserNumbers(), randomNumbersProvider.returnWinningNumbers());
    }
}
