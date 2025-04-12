package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.UserRankingDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
class UserRankingRetriever {

    private final UserGameSessionRepository repository;

    List<UserRankingDto> getRanking(final Long daysToSubstract) {

        LocalDate date = LocalDate.now().minusDays(daysToSubstract);

        List<UserGameSessionResult> ranking = repository.findAllByDate(date);

        if (ranking != null && !ranking.isEmpty()) {
            return ranking.stream()
                    .sorted(
                            Comparator.comparing(UserGameSessionResult::getHasWon).reversed() // true przed false
                                    .thenComparing(UserGameSessionResult::getAttempts)
                    )
                    .map(userGameSessionResult -> new UserRankingDto(
                            userGameSessionResult.getUserId(),
                            userGameSessionResult.getAttempts(),
                            userGameSessionResult.getMessage()))
                    .toList();
        }
        return null;
    }
}
