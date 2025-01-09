package console;

import app.guessnumber.GuessNumber;
import app.guessnumber.UserNumberProviderInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserNumberProvider implements UserNumberProviderInterface {
    int userNumber;
    int temporaryUserNumber;
    Scanner scanner = new Scanner(System.in);

    @Override
    public int returnUserNumber() {
        System.out.println("Podaj liczbę z zakresu " + GuessNumber.LOWEST_NUMBER + "-" + GuessNumber.HIGHEST_NUMBER);
        try {
            temporaryUserNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Podana wartość nie jest liczbą całkowitą");
            scanner.nextLine();
        }
        if (temporaryUserNumber >= GuessNumber.LOWEST_NUMBER && temporaryUserNumber <= GuessNumber.HIGHEST_NUMBER) {
            userNumber = temporaryUserNumber;
        } else {
            System.out.println("Należy podać liczbę z zakresu " + GuessNumber.LOWEST_NUMBER + "-" + GuessNumber.HIGHEST_NUMBER);
        }
        return userNumber;
    }
}

