package Lotek;

import java.util.Random;
import java.util.Scanner;

import static Lotek.LotekRandomNumbersProvider.HIGHEST_NUMBER;
import static Lotek.LotekRandomNumbersProvider.LOWEST_NUMBER;


public class LotekUserNumbersProvider {

    int[] userNumbers = new int[6];
    int[] temporaryUserNumbers = new int[6];

    int[] insertUserNumbers() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < userNumbers.length; i++) {
            System.out.print("Wprowadź liczbę: ");
            temporaryUserNumbers[i] = scanner.nextInt();
            if (temporaryUserNumbers[i] >= LOWEST_NUMBER && temporaryUserNumbers[i] <= HIGHEST_NUMBER) {
                userNumbers[i] = temporaryUserNumbers[i];
            } else {
                System.out.println("Należy podać liczbę z zakresu 1-99");
                i--;
            }
        }
        return userNumbers;
    }
}
