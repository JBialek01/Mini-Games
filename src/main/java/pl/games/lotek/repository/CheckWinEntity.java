package pl.games.lotek.repository;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "checkWin")
@Getter
public class CheckWinEntity {

    @Id
    private String id;
    private String userId;
    private LocalDate date;
    private String userNumbersId;
    private String userNumbers;
    private String winningNumbers;
    private String hits;

    public CheckWinEntity() {
    }

    public CheckWinEntity(String userId, String userNumbersId, String userNumbers, LocalDate date, String winningNumbers, String hits) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public CheckWinEntity(String userId, LocalDate date, String userNumbersId, String userNumbers, String winningNumbers, String hits) {
        this.userId = userId;
        this.date = date;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }
}

