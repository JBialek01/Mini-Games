package pl.games.lotek.domain.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

interface UserResultsRepository extends MongoRepository<UserResults, String> {

    boolean existsByUserIdAndUserNumbersId(String userId, String userNumbersId);

    List<UserResults> findByUserIdAndDateBetween(String userId, Instant fromTime, Instant toTime);

    List<UserResults> findByDateBetween(Instant fromTime, Instant toTime);
}