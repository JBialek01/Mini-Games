package Lotek;

import java.util.Random;
import java.util.Scanner;

public class LotekGame {

    int[] temporaryUserNumbers = new int[6];
    int[] userNumbers = new int[6];
    int[] winningNumbers = new int[6];
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 99;

    public void lotekGame() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        for (int i = 0; i < userNumbers.length; i++) {
            System.out.print("Wprowadź liczbę: ");
            temporaryUserNumbers[i] = scanner.nextInt();
            if (temporaryUserNumbers[i] >= LOWEST_NUMBER && temporaryUserNumbers[i] <= HIGHEST_NUMBER) {
                userNumbers[i] = temporaryUserNumbers[i];
            }
            else {
                System.out.println("Należy podać liczbę z zakresu 1-99");
                i--;
            }
        }

        for (int i = 0; i < winningNumbers.length; i++) {
            winningNumbers[i] = rand.nextInt(LOWEST_NUMBER,HIGHEST_NUMBER);
        }

        LotekResultShower.showResult(userNumbers, winningNumbers);
    }
}
