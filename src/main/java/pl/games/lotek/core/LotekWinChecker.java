package pl.games.lotek.core;

import java.util.Set;
import java.util.TreeSet;

public class LotekWinChecker {

    private String message;
    private final UserNumbersProvider userNumbersProvider;

    public LotekWinChecker(UserNumbersProvider userNumbersProvider) {
        this.userNumbersProvider = userNumbersProvider;
    }

    public String checkResults(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        Set<Integer> matchedNumbers = new TreeSet<>(userNumbers);
        matchedNumbers.retainAll(winningNumbers);
        int matchedCount = matchedNumbers.size();
        return buildMessage(winningNumbers, matchedCount);
    }

    private String buildMessage(Set<Integer> winningNumbers, int matchedCount) {
        message = "Ilość trafionych liczb: " + matchedCount + ", ";
        if (matchedCount == winningNumbers.size()) {
            message += "Gratulacje! Wygrałeś!";
        } else {
            message += "Tym razem się nie udało, spróbuj ponownie";
        }
        return message;
    }
}
