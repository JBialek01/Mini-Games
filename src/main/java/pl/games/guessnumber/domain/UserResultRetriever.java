package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
class UserResultRetriever {

    private final UserGameSessionRepository userGameSessionRepository;

    NumberSubmissionDto getResultForSpecifiedUserAndDay(final String userId, final Integer days) {
        LocalDate date = LocalDate.now().minusDays(days);
        Optional<UserGameSessionResult> result = userGameSessionRepository.findByUserIdAndDate(userId, date);
        if (result.isPresent()) {
            return new NumberSubmissionDto(result.get().getAttempts(), result.get().getMessage());
        } else {
            return new NumberSubmissionDto(0, "No result found for the specified day");
        }
    }
}
