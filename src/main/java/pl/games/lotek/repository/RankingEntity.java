package pl.games.lotek.repository;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "ranking")
@Getter
public class RankingEntity {

    @Id
    private String id;
    private LocalDate date;
    private String hits;
    private String userId;

    public RankingEntity() {
    }

    public RankingEntity(LocalDate date, String hits, String userId) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.hits = hits;
        this.userId = userId;
    }

    public RankingEntity(String id, LocalDate date, String hits, String userId) {
        this.id = id;
        this.date = date;
        this.hits = hits;
        this.userId = userId;
    }


}
