package app.lotek;

import java.util.Set;

class LotekResultShower {

    private final LotekWinChecker lotekWinChecker = new LotekWinChecker();

    void showResult(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        System.out.println("Twoje liczby:");
        for (int userNumber : userNumbers) {
            System.out.print("[" + userNumber + "] ");
        }
        System.out.println("\nZwycięskie liczby:");
        for (int winningNumber : winningNumbers) {
            System.out.print("[" + winningNumber + "] ");
        }
        lotekWinChecker.checkResults(userNumbers, winningNumbers);
    }
}
