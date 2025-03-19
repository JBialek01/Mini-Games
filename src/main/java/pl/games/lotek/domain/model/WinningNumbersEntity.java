package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Document(collection = "winningNumbers")
@Getter
public class WinningNumbersEntity {

    @Id
    private String id;
    private LocalDate date;
    private Set<Integer> winningNumbers;

    public WinningNumbersEntity() {
    }

    public WinningNumbersEntity(LocalDate date, Set<Integer> winningNumbers) {
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.id = UUID.randomUUID().toString();
    }

    public WinningNumbersEntity(String id, LocalDate date, Set<Integer> winningNumbers) {
        this.id = id;
        this.date = date;
        this.winningNumbers = winningNumbers;
    }
}
