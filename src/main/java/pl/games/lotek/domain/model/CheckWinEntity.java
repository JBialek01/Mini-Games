package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;
import java.util.Set;
import java.util.UUID;

@Document(collection = "checkWin")
@Getter
public class CheckWinEntity {

    @Id
    private String id;
    private String userId;
    private Instant date;
    private String userNumbersId;
    private Set<Integer> userNumbers;
    private Set<Integer> winningNumbers;
    private Integer hits;

    public CheckWinEntity() {
    }

    public CheckWinEntity(String userId, String userNumbersId, Set<Integer> userNumbers, LocalDateTime date, Set<Integer> winningNumbers, Integer hits) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.date = date.toInstant(ZoneOffset.UTC);
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }
}

