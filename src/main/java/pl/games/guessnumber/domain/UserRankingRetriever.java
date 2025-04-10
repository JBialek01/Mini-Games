package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.UserRankingDto;

import java.time.LocalDate;
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
                    .sorted((a, b) -> {
                        if (a.getHasWon() && !b.getHasWon()) return -1;
                        if (!a.getHasWon() && b.getHasWon()) return 1;
                        return Integer.compare(a.getAttempts(), b.getAttempts());
                    })
                    .map(userGameSessionResult -> new UserRankingDto(
                            userGameSessionResult.getUserId(),
                            userGameSessionResult.getAttempts(),
                            userGameSessionResult.getMessage()))
                    .toList();
        }
        return null;
    }
}
