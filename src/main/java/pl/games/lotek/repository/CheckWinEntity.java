package pl.games.lotek.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "checkWin")
public class CheckWinEntity {

    @Id
    private String id;
    private String userId;
    private LocalDate date;
    private String userNumbers;
    private String winningNumbers;
    private String hits;

    public CheckWinEntity() {
    }

    public CheckWinEntity(String userId, String userNumbers, LocalDate date, String winningNumbers, String hits) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public CheckWinEntity(String userId, LocalDate date, String userNumbers, String winningNumbers, String hits) {
        this.userId = userId;
        this.date = date;
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public String getHits() {
        return hits;
    }

    public String getUserNumbers() {
        return userNumbers;
    }

    public LocalDate getDate() {
        return date;
    }
}

