package pl.games.lotek.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "lotekTickets")
public class LotekTicketEntity {

    @Id
    private String id;
    private String userId;
    private String userNumbers;
    private LocalDate date;

    public LotekTicketEntity() {
    }

    public LotekTicketEntity(String userId, String userNumbers, LocalDate date) {
        this.userId = userId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserNumbers() {
        return userNumbers;
    }

    public LocalDate getDate() {
        return date;
    }
}
