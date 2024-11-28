package app.lotek;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LotekRandomNumbersProvider {
    private final Set<Integer> randomNumbers = new TreeSet<>();

    Set<Integer> returnWinningNumbers() {
        Random rand = new Random();
        for (int i = 0; i < LotekGame.NUMBERS_COUNT; i++) {
            randomNumbers.add(rand.nextInt(LotekGame.LOWEST_NUMBER, LotekGame.HIGHEST_NUMBER));
        }
        return randomNumbers;
    }
}
