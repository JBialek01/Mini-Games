package pl.games.lotek.repository;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "winningNumbers")
@Getter
public class WinningNumberEntity {

    @Id
    private String id;
    private LocalDate date;
    private String winningNumbers;

    public WinningNumberEntity() {
    }

    public WinningNumberEntity(LocalDate date, String winningNumbers) {
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.id = UUID.randomUUID().toString();
    }

    public WinningNumberEntity(String id, LocalDate date, String winningNumbers) {
        this.id = id;
        this.date = date;
        this.winningNumbers = winningNumbers;
    }
}
