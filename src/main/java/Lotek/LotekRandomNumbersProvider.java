package Lotek;

import java.util.Random;

public class LotekRandomNumbersProvider {
    int[] winningNumbers = new int[6];
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 99;

    int[] returnWinningNumbers() {
        Random rand = new Random();
        for (int i = 0; i < winningNumbers.length; i++) {
            winningNumbers[i] = rand.nextInt(LOWEST_NUMBER, HIGHEST_NUMBER);
        }
        return winningNumbers;
    }
}
