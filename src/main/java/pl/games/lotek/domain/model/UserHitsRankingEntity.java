package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "ranking")
@Getter
public class UserHitsRankingEntity {

    @Id
    private String id;
    private LocalDate date;
    private Integer hits;
    private String userId;

    public UserHitsRankingEntity() {
    }

    public UserHitsRankingEntity(LocalDate date, Integer hits, String userId) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.hits = hits;
        this.userId = userId;
    }

    public UserHitsRankingEntity(String id, LocalDate date, Integer hits, String userId) {
        this.id = id;
        this.date = date;
        this.hits = hits;
        this.userId = userId;
    }


}
