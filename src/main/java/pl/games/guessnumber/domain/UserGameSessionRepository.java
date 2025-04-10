package pl.games.guessnumber.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface UserGameSessionRepository extends MongoRepository<UserGameSessionResult, String> {

    Optional<UserGameSessionResult> findByUserIdAndDate(String userId, LocalDate date);

    List<UserGameSessionResult> findAllByDate(LocalDate date);
}
