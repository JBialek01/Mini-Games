package pl.games.lotek.repository;

import java.util.UUID;

public class LotekEntity {
    private final String id;
    private final String userNumbers;
    private final String winningNumbers;

    public LotekEntity(String userNumbers, String winningNumbers) {
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
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
