package pl.games.lotek.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.games.lotek.domain.model.CheckWinEntity;

import java.time.LocalDate;
import java.util.List;

public interface CheckWinRepository extends MongoRepository<CheckWinEntity, String> {
    List<CheckWinEntity> findByUserIdAndDate(String userId, LocalDate date);
    List<CheckWinEntity> findByUserIdAndUserNumbersIdAndDate(String userId, String userNumbersId, LocalDate date);
    List<CheckWinEntity> findByDate(LocalDate date);
}

