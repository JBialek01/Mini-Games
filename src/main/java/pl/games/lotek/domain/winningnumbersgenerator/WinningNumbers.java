package pl.games.lotek.domain.winningnumbersgenerator;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

@Document(collection = "lotek_winningNumbers")
@Getter
class WinningNumbers {

    @Id
    private String id;
    private Instant date;
    private Set<Integer> winningNumbers;

    public WinningNumbers() {
    }

    public WinningNumbers(LocalDateTime date, Set<Integer> winningNumbers) {
        this.date = date.toInstant(ZoneOffset.UTC);
        this.winningNumbers = winningNumbers;
        this.id = UUID.randomUUID().toString();
    }

    public Instant getTimestamp() {
        return date;
    }
}
