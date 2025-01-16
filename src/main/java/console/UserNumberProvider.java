package console;

import app.guessnumber.UserNumberProviderInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserNumberProvider implements UserNumberProviderInterface {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    int userNumber;
    int temporaryUserNumber;
    Scanner scanner = new Scanner(System.in);

    @Override
    public int returnUserNumber() {
        System.out.println("Podaj liczbę z zakresu " + LOWEST_NUMBER + "-" + HIGHEST_NUMBER);
        try {
            temporaryUserNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Podana wartość nie jest liczbą całkowitą");
            scanner.nextLine();
        }
        if (temporaryUserNumber >= LOWEST_NUMBER && temporaryUserNumber <= HIGHEST_NUMBER) {
            userNumber = temporaryUserNumber;
        } else {
            System.out.println("Należy podać liczbę z zakresu " + LOWEST_NUMBER + "-" + HIGHEST_NUMBER);
        }
        return userNumber;
    }
}

