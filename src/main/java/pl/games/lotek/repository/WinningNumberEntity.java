package pl.games.lotek.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "winningNumbers")
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

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }
}
