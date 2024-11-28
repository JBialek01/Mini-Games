package app.lotek;

import java.util.Set;
import java.util.TreeSet;

class LotekWinChecker {

    public void checkResults(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        Set<Integer> matchedNumbers = new TreeSet<>(userNumbers);
        matchedNumbers.retainAll(winningNumbers);
        System.out.println("\n\nIlość trafionych liczb: " + matchedNumbers.size());
        if (matchedNumbers.size() == winningNumbers.size()) {
            System.out.println("\nGratulacje! Wygrałeś");
        } else {
            System.out.println("\nTym razem się nie udało, spróbuj ponownie");
        }
    }
}
