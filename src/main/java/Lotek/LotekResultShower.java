package Lotek;

class LotekResultShower {


    static void showResult(int[] userNumbers, int[] winningNumbers) {

        System.out.println("Twoje liczby:");
        for (int userNumber : userNumbers) {
            System.out.print("[" + userNumber + "] ");
        }
        System.out.println("\nZwycięskie liczby:");
        for (int winningNumber : winningNumbers) {
            System.out.print("[" + winningNumber + "] ");
        }
        LotekWinChecker.checkResults(userNumbers, winningNumbers);
    }
}
