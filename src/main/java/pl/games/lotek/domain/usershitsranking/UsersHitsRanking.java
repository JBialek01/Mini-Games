package pl.games.lotek.domain.usershitsranking;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Document(collection = "lotek_userHitsRanking")
@Getter
class UsersHitsRanking {

    @Id
    private String id;
    private Instant date;
    private Integer hits;
    private String userId;

    public UsersHitsRanking() {
    }

    public UsersHitsRanking(ZonedDateTime date, Integer hits, String userId) {
        this.id = UUID.randomUUID().toString();
        this.date = date.toInstant();
        this.hits = hits;
        this.userId = userId;
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }

    public Instant getTimestamp() {
        return date;
    }
}
