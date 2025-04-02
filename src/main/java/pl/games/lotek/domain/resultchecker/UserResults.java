package pl.games.lotek.domain.resultchecker;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Document(collection = "checkResults")
@Getter
class UserResults {

    @Id
    private String id;
    private String userId;
    private Instant date;
    private String userNumbersId;
    private Set<Integer> userNumbers;
    private Set<Integer> winningNumbers;
    private Integer hits;

    public UserResults() {
    }

    public UserResults(String userId, String userNumbersId, Set<Integer> userNumbers, Instant date, Set<Integer> winningNumbers, Integer hits) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userNumbersId = userNumbersId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.hits = hits;
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }
}

