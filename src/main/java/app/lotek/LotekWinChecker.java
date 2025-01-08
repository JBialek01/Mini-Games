package app.lotek;

import java.util.Set;
import java.util.TreeSet;

class LotekWinChecker {

    private String message;

    public String checkResults(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        Set<Integer> matchedNumbers = new TreeSet<>(userNumbers);
        matchedNumbers.retainAll(winningNumbers);
        message = ("\n\nIlość trafionych liczb: " + matchedNumbers.size());
        System.out.println(message);
        if (matchedNumbers.size() == winningNumbers.size()) {
            message = ("\nGratulacje! Wygrałeś");
            System.out.println(message);
        } else {
            message = ("\nTym razem się nie udało, spróbuj ponownie");
            System.out.println(message);
        }
        return message;
    }
}
