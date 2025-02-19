package pl.games.guessnumber.web;

import pl.games.guessnumber.core.WinningNumberProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Random;

@Component
@SessionScope
public class GuessNumberSession {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    private int winningNumber;
    WinningNumberProvider winningNumberProvider;

    public GuessNumberSession() {
        this.winningNumber = new Random().nextInt(LOWEST_NUMBER, HIGHEST_NUMBER);
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    // można dodać logikę resetu, itp
}
