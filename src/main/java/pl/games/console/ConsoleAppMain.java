package pl.games.console;

import pl.games.app.Game;
import pl.games.app.lotek.LotekGame;
import pl.games.app.lotek.LotekRandomNumbersProvider;
import pl.games.app.lotek.LotekRepositoryInMemoryDb;
import pl.games.app.lotek.LotekUserNumbersProvider;

import java.util.List;
import java.util.Scanner;

public class ConsoleAppMain {
    public static void main(String[] args) {
        Game lotekGame = new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider(), new LotekRepositoryInMemoryDb());
        Game guessNumberConsoleGame = new GuessNumberConsoleGame(new UserNumberProvider());
        Scanner scanner = new Scanner(System.in);
        GameSelector gameSelector = new GameSelector(List.of(lotekGame, guessNumberConsoleGame), scanner);
        gameSelector.selectGame();
    }
}
