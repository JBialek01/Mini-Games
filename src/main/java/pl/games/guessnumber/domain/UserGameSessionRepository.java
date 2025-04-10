package pl.games.guessnumber.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

interface UserGameSessionRepository extends MongoRepository<UserGameSessionResult, String> {

    Optional<UserGameSessionResult> findByUserIdAndDate(String userId, LocalDate date);
}
