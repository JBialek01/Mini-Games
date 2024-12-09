package app;

import app.guessnumber.GuessNumber;
import app.guessnumber.UserNumberProvider;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;
import app.lotek.LotekGame;
import app.lotek.LotekRandomNumbersProvider;
import app.lotek.LotekUserNumbersProvider;

import java.util.InputMismatchException;
import java.util.Scanner;

class GameSelector {

    String message;

    public int selectGame(){
    Scanner scanner = new Scanner(System.in);
//    Set<Game> game = new HashSet<>();
//    game.add(new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider()));
//    game.add(new GuessNumber(new WinningNumberProvider(), new WinChecker(new UserNumberProvider())));
    int gamePicker = 99;
        while (gamePicker != 0) {
            System.out.println("\nWybierz grę:");
            System.out.println("1. - Lotek");
            System.out.println("2. - GuessNumber");
            System.out.println("0. - Zakończ program");
            try {
                gamePicker = scanner.nextInt();
            } catch (InputMismatchException e) {
                message = "Podana wartość nie jest liczbą całkowitą";
                System.err.println(message);
                scanner.nextLine();
            }
            switch (gamePicker) {
                case 0: {
                    break;
                }
                case 1: {
                    message = "Wybrałeś grę Lotek";
                    System.out.println(message);
                    Game lotekGame = new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider());
                    lotekGame.startGame();
                    break;
                }
                case 2: {
                    message = "Wybrałeś grę GuessNumber";
                    System.out.println(message);
                    Game guessNumber = new GuessNumber(new WinningNumberProvider(), new WinChecker(new UserNumberProvider()));
                    guessNumber.startGame();
                    break;
                }
                default: {
                    message = "Wprowadź poprawny numer gry";
                    System.out.println(message);
                }
            }
        }
        return gamePicker;
    }
}
