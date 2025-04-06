package pl.games.lotek.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.util.TimeService;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
class UserResultsRetriever {

    private final UserResultsRepository userResultsRepository;
    private final UserResultsChecker userResultsChecker;

    List<UserResultsDto> getResults(String userId) {
        userResultsChecker.checkAndSaveResults(userId);
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        List<UserResults> results = userResultsRepository.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);
        return UserResultsMapper.mapToUserResultsDto(results);
    }

    List<UserResultsDto> getResultsForSpecifiedDay(final String userId, final Long days) {
        Instant startOfSpecifiedDay = TimeService.getStartOfSpecifiedDay(days);
        Instant endOfSpecifiedDay = TimeService.getEndOfSpecifiedDay(days);
        List<UserResults> results = userResultsRepository.findByUserIdAndDateBetween(userId, startOfSpecifiedDay, endOfSpecifiedDay);
        return UserResultsMapper.mapToUserResultsDto(results);
    }

    List<UserResultsDto> findByDateBetween(final Instant startOfPreviousDay, final Instant endOfPreviousDay) {
        List<UserResults> results = userResultsRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        return UserResultsMapper.mapToUserResultsDto(results);
    }
}
