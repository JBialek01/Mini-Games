package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;
import java.util.Set;
import java.util.UUID;

@Document(collection = "winningNumbers")
@Getter
public class WinningNumbersEntity {

    @Id
    private String id;
    private Instant date;
    private Set<Integer> winningNumbers;

    public WinningNumbersEntity() {
    }

    public WinningNumbersEntity(LocalDateTime date, Set<Integer> winningNumbers) {
        this.date = date.toInstant(ZoneOffset.UTC);
        this.winningNumbers = winningNumbers;
        this.id = UUID.randomUUID().toString();
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }
}
