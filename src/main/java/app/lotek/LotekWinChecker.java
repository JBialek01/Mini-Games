package app.lotek;

import java.util.Set;
import java.util.TreeSet;

public class LotekWinChecker {

    private String message;
    private final UserNumbersProviderInterface userNumbersProvider;

    public LotekWinChecker(UserNumbersProviderInterface userNumbersProvider) {
        this.userNumbersProvider = userNumbersProvider;
    }

    public String checkResults(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        Set<Integer> matchedNumbers = new TreeSet<>(userNumbers);
        matchedNumbers.retainAll(winningNumbers);
        int matchedCount = matchedNumbers.size();
        return buildMessage(winningNumbers, matchedCount);
    }

    private String buildMessage(Set<Integer> winningNumbers, int matchedCount) {
        message = "Ilość trafionych liczb: " + matchedCount;
        if (matchedCount == winningNumbers.size()) {
            message += "\nGratulacje! Wygrałeś!";
        } else {
            message += "\nTym razem się nie udało, spróbuj ponownie";
        }
        return message;
    }
}
