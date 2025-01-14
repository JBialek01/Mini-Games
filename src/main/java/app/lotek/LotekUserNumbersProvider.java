package app.lotek;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LotekUserNumbersProvider implements UserNumbersProviderInterface {

    private final Set<Integer> userNumbers = new TreeSet<>();
    int userNumber;

    Set<Integer> returnUserNumbers() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Wprowadź liczbę: ");
            try{
                userNumber = scanner.nextInt();
            } catch (InputMismatchException e){
                System.err.println("Podana wartość nie jest liczbą całkowitą");
                scanner.nextLine();
            }
            if (userNumbers.contains(userNumber)) {
                System.out.println("Liczba została już wcześniej podana");
            }
            if (userNumber >= LotekGame.LOWEST_NUMBER && userNumber <= LotekGame.HIGHEST_NUMBER) {
                userNumbers.add(userNumber);
            } else {
                System.out.println("Należy podać liczbę z zakresu 1-99");
            }

        } while (userNumbers.size() < LotekGame.NUMBERS_COUNT);
        return userNumbers;
    }
}
