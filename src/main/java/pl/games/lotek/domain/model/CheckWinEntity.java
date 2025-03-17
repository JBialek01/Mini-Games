package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Document(collection = "checkWin")
@Getter
public class CheckWinEntity {

    @Id
    private String id;
    private String userId;
    private LocalDate date;
    private String userNumbersId;
    private Set<Integer> userNumbers;
    private Set<Integer> winningNumbers;
    private Integer hits;

    public CheckWinEntity() {
    }

    public CheckWinEntity(String userId, String userNumbersId, Set<Integer> userNumbers, LocalDate date, Set<Integer> winningNumbers, Integer hits) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public CheckWinEntity(String userId, LocalDate date, String userNumbersId, Set<Integer> userNumbers, Set<Integer> winningNumbers, Integer hits) {
        this.userId = userId;
        this.date = date;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }
}

