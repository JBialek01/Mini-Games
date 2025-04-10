package pl.games.guessnumber.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "guessnumber_userNumbers")
@Getter
class UserNumber {

    @Id
    private String id;
    private String userId;
    private Instant date;
    private Integer number;

    public UserNumber() {
    }

    public UserNumber(String userId, Instant date, Integer number) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.date = date;
        this.number = number;
    }
}
