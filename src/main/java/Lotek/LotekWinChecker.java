package Lotek;

import java.util.Arrays;

public class LotekWinChecker {

    public static void checkResults(int[] userNumbers, int[] winningNumbers){
        if(Arrays.equals(userNumbers, winningNumbers)) {
            System.out.println("\nGratulacje! Wygrałeś");
        }
        else {
            System.out.println("\nTym razem się nie udało, spróbuj ponownie");
        }
    }
}
