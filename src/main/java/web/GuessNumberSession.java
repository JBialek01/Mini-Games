package web;

import app.guessnumber.GuessNumber;
import app.guessnumber.WinningNumberProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Random;

@Component
@SessionScope
public class GuessNumberSession {
    private int winningNumber;
    WinningNumberProvider winningNumberProvider;

    public GuessNumberSession() {
        this.winningNumber = new Random().nextInt(GuessNumber.LOWEST_NUMBER, GuessNumber.HIGHEST_NUMBER);
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    // można dodać logikę resetu, itp
}
