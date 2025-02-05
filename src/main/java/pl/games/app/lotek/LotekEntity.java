package pl.games.app.lotek;

import java.util.UUID;

public class LotekEntity {
    private final String id;
    private final String userNumbers;
    private final String winningNumbers;

    public LotekEntity(String winningNumbers, String userNumbers) {
        this.winningNumbers = winningNumbers;
        this.userNumbers = userNumbers;
        this.id = UUID.randomUUID().toString();
    }

    public LotekEntity(String id, String userNumbers, String winningNumbers) {
        this.id = id;
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
    }

    public String getId() {
        return id;
    }

    public String getUserNumbers() {
        return userNumbers;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }
}
