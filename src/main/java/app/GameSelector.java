package app;

import app.guessnumber.GuessNumber;
import app.guessnumber.WinningNumberProvider;
import app.lotek.LotekGame;
import app.lotek.LotekRandomNumbersProvider;
import app.lotek.LotekUserNumbersProvider;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

class GameSelector {
    public void selectGame(){
    Scanner scanner = new Scanner(System.in);
    Set<Game> game = new HashSet<>();
    game.add(new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider()));
    game.add(new GuessNumber(new WinningNumberProvider()));
    int gamePicker = 99;
        while (gamePicker != 0) {
            System.out.println("\nWybierz grę:");
            System.out.println("1. - Lotek");
            System.out.println("2. - GuessNumber");
            System.out.println("0. - Zakończ program");
            try {
                gamePicker = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Podana wartość nie jest liczbą całkowitą");
                scanner.nextLine();
            }
            switch (gamePicker) {
                case 0: {
                    break;
                }
                case 1: {
                    Game lotekGame = new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider());
                    lotekGame.startGame();
                    break;
                }
                case 2: {
                    Game guessNumber = new GuessNumber(new WinningNumberProvider());
                    guessNumber.startGame();
                    break;
                }
                default: {
                    System.out.println("Wprowadź poprawny numer gry");
                }
            }
        }
    }
}
