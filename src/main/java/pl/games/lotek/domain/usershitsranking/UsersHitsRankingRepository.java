package pl.games.lotek.domain.usershitsranking;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

interface UsersHitsRankingRepository extends MongoRepository<UsersHitsRanking, String> {

    List<UsersHitsRanking> findByDateBetweenAndUserId(Instant fromTime, Instant toTime, String userId);

    List<UsersHitsRanking> findByDateBetween(Instant fromTime, Instant toTime);
}
