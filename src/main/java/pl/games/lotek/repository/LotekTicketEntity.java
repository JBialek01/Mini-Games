package pl.games.lotek.repository;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "lotekTickets")
@Getter
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
}
