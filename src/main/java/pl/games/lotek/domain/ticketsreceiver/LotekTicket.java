package pl.games.lotek.domain.ticketsreceiver;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Document(collection = "lotek_tickets")
@Getter
class LotekTicket {

    @Id
    private String id;
    private String userId;
    private Set<Integer> userNumbers;
    private Instant date;

    public LotekTicket() {
    }

    public LotekTicket(String userId, Set<Integer> userNumbers, Instant date) {
        this.userId = userId;
        this.userNumbers = userNumbers;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }

    public Instant getTimestamp() {
        return date;
    }
}
