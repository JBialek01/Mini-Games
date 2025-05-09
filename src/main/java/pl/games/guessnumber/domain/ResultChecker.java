package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;
import pl.games.guessnumber.domain.dto.SavedNumberDto;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
class ResultChecker {

    private final WinningNumberProviderInterface winningNumberProvider;
    private final UserGameSessionRepository sessionRepository;

    NumberSubmissionDto checkResult(String userId, SavedNumberDto savedNumberDto) {
        Integer winningNumber = winningNumberProvider.getWinningNumber(0L).number();
        Integer guess = savedNumberDto.number();

        UserGameSessionResult session = sessionRepository
                .findByUserIdAndDate(userId, LocalDate.now(ZoneOffset.UTC))
                .orElse(new UserGameSessionResult(userId, LocalDate.now(ZoneOffset.UTC)));

        if (session.getHasWon()) {
            return new NumberSubmissionDto(session.getAttempts(), "You already guessed it today, congratulations!");
        }

        session.incrementAttempts();

        if (guess.equals(winningNumber)) {
            session.setHasWon(true);
            session.setMessage("You guessed it, congratulations!");
            sessionRepository.save(session);
            return new NumberSubmissionDto(session.getAttempts(), session.getMessage());
        }

        sessionRepository.save(session);

        if (guess < winningNumber) {
            session.setMessage("Provided number is too low");
            sessionRepository.save(session);
            return new NumberSubmissionDto(session.getAttempts(), session.getMessage());
        } else {
            session.setMessage("Provided number is too high");
            sessionRepository.save(session);
            return new NumberSubmissionDto(session.getAttempts(), session.getMessage());
        }
    }
}
