package pl.games.guessnumber.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "guessnumber_winningNumbers")
@Getter
class WinningNumber {

    @Id
    private String id;
    private Instant date;
    private Integer number;

    public WinningNumber() {
    }

    public WinningNumber(Integer number, Instant date) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.number = number;
    }
}
