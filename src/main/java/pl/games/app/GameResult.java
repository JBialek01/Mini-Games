package pl.games.app;

import java.util.Set;

public class GameResult {

    private final Set<Integer> userNumbers;
    private final Set<Integer> winningNumbers;
    private final String message;

    public GameResult(Set<Integer> userNumbers, Set<Integer> winningNumbers, String message) {
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
        this.message = message;
    }

    public Set<Integer> getUserNumbers() {
        return userNumbers;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "userNumbers=" + userNumbers +
                ", winningNumbers=" + winningNumbers +
                ", message='" + message + '\'' +
                '}';
    }
}
