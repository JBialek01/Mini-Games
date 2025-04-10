package pl.games.guessnumber.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "guessnumber_userGameSessionResults")
@Getter
class UserGameSessionResult {

    @Id
    private String id;
    private String userId;
    private Instant createdAt;
    private LocalDate date;
    private Integer attempts;
    private Boolean hasWon;
    private String message;

    public UserGameSessionResult() {
    }

    public UserGameSessionResult(String userId, LocalDate date) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.createdAt = Instant.now();
        this.date = date;
        this.attempts = 0;
        this.hasWon = false;
        this.message = "";
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
