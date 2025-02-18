package pl.games.lotek.core;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@Component
public class LotekRandomNumbersProvider {
    private final Set<Integer> randomNumbers = new TreeSet<>();

    Set<Integer> returnWinningNumbers() {
        Random rand = new Random();
        while (randomNumbers.size() < LotekGame.NUMBERS_COUNT) {
            int number = rand.nextInt(
                    LotekGame.HIGHEST_NUMBER - LotekGame.LOWEST_NUMBER + 1
            ) + LotekGame.LOWEST_NUMBER;
            randomNumbers.add(number);
        }
        return randomNumbers;
    }
}
