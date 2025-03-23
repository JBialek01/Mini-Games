package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;
import java.util.Set;
import java.util.UUID;

@Document(collection = "lotekTickets")
@Getter
public class LotekTicketEntity {

    @Id
    private String id;
    private String userId;
    private Set<Integer> userNumbers;
    private Instant date;

    public LotekTicketEntity() {
    }

    public LotekTicketEntity(String userId, Set<Integer> userNumbers, LocalDateTime date) {
        this.userId = userId;
        this.userNumbers = userNumbers;
        this.date = date.toInstant(ZoneOffset.UTC);
        this.id = UUID.randomUUID().toString();
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }
}
