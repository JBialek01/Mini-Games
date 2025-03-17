package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;

import java.time.LocalDate;
import java.util.List;

public interface LotekRepository extends MongoRepository<LotekTicketEntity, String> {

    LotekTicketEntity save(LotekTicketEntity lotekTicketEntity);

    List<LotekTicketEntity> findAll();

    List<LotekTicketEntity> findByUserId(String userId);

    List<LotekTicketEntity> findByDate(LocalDate previousDay);

    List<LotekTicketEntity> findByUserIdAndDate(String userId, LocalDate previousDay);

}
