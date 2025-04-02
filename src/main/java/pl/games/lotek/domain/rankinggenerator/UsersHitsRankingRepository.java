package pl.games.lotek.domain.rankinggenerator;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

interface UsersHitsRankingRepository extends MongoRepository<UsersHitsRanking, String> {

    List<UsersHitsRanking> findByDateBetweenAndUserId(Instant startOfPreviousDay, Instant endOfPreviousDay, String userId);
    List<UsersHitsRanking> findByDateBetween(Instant startOfPreviousDay, Instant endOfPreviousDay);
}
