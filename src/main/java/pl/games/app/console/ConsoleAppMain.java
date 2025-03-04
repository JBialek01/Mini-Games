package pl.games.app.console;

import pl.games.app.core.Game;
import pl.games.lotek.core.LotekGame;
import pl.games.lotek.core.LotekRandomNumbersProvider;
import pl.games.lotek.repository.LotekRepositoryInMemoryDb;
import pl.games.lotek.console.LotekUserNumbersConsoleProvider;
import pl.games.guessnumber.console.GuessNumberConsoleGame;
import pl.games.guessnumber.console.UserNumberProvider;

import java.util.List;
import java.util.Scanner;

public class ConsoleAppMain {
    public static void main(String[] args) {
        Game lotekGame = new LotekGame(new LotekUserNumbersConsoleProvider(), new LotekRandomNumbersProvider(), new LotekRepositoryInMemoryDb());
        Game guessNumberConsoleGame = new GuessNumberConsoleGame(new UserNumberProvider());
        Scanner scanner = new Scanner(System.in);
        GameSelector gameSelector = new GameSelector(List.of(lotekGame, guessNumberConsoleGame), scanner);
        gameSelector.selectGame();
    }
}
