package console;

import app.Game;
import app.GameResult;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class GameSelector {

    String message;
    List<Game> games;
    Scanner scanner;

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
                message = showResultConsole(result.getUserNumbers(), result.getWinningNumbers(), result.getMessage());
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

    private String showResultConsole(Set<Integer> userNumbers, Set<Integer> winningNumbers, String checkResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("Twoje liczby:\n");
        for (int userNumber : userNumbers) {
            sb.append("[").append(userNumber).append("] ");
        }
        sb.append("\nZwycięskie liczby:\n");
        for (int winningNumber : winningNumbers) {
            sb.append("[").append(winningNumber).append("] ");
        }
        sb.append("\n").append(checkResult);
        return sb.toString();
    }
}
