package console;

import app.Game;
import app.guessnumber.GuessNumber;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;
import app.lotek.LotekGame;
import app.lotek.LotekRandomNumbersProvider;
import app.lotek.LotekUserNumbersProvider;

import java.util.List;
import java.util.Scanner;

public class ConsoleAppMain {
    public static void main(String[] args) {
        Game lotekGame = new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider());
        Game guessNumberConsoleGame = new GuessNumberConsoleGame(new UserNumberProvider());
        Scanner scanner = new Scanner(System.in);
        GameSelector gameSelector = new GameSelector(List.of(lotekGame, guessNumberConsoleGame), scanner);
        gameSelector.selectGame();
    }
}
