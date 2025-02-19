package pl.games.app.console;

import pl.games.app.core.Game;
import pl.games.app.core.GameResult;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameSelector {

    String message;
    List<Game> games;
    Scanner scanner;
    ConsoleResultShower consoleResultShower = new ConsoleResultShower();

    public GameSelector(List<Game> games, Scanner scanner) {
        this.games = games;
        this.scanner = scanner;
    }

    public int selectGame() {
        if (games.isEmpty()) {
            System.out.println("Brak dostępnych gier.");
            return -1;
        }

    int gamePicker = 99;
        while (gamePicker != 0) {
            System.out.println("\nWybierz grę:");
            AtomicInteger i = new AtomicInteger(1);
            games.forEach(game -> System.out.println(i.getAndIncrement() + ". - " + game.getName()));
            System.out.println("0. - Zakończ program");
            try {
                gamePicker = scanner.nextInt();
                scanner.nextLine();
                if (gamePicker < 0 || gamePicker > games.size()) {
                    System.err.println("Niepoprawny wybór. Wybierz liczbę z zakresu od 0 do " + games.size());
                    continue;
                }
                if (gamePicker == 0) {
                    System.out.println("Koniec programu.");
                    return 0;
                }
                Game game = games.get(gamePicker - 1);
                System.out.println("Wybrałeś grę: " + game.getName());
                GameResult result = game.startGame();
                message = consoleResultShower.showResultConsole(result.getUserNumbers(), result.getWinningNumbers(), result.getMessage());
                System.out.println(result);
            } catch (InputMismatchException e) {
                message = "Podana wartość nie jest liczbą całkowitą";
                System.err.println(message);
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Wybrano nieistniejącą grę. Wybierz ponownie.");
            }
        }
        return gamePicker;
    }
}
