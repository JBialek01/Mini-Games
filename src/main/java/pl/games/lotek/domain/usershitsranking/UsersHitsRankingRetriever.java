package pl.games.lotek.domain.usershitsranking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.usershitsranking.dto.UsersHitsRankingDto;
import pl.games.lotek.domain.util.TimeService;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UsersHitsRankingRetriever {

    private final UsersHitsRankingRepository usersHitsRankingRepository;

    List<UsersHitsRankingDto> fetchAllRankingEntries() {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        return UsersHitsRankingMapper.mapToRankingDto(fetchSortedRanking(startOfPreviousDay, endOfPreviousDay));
    }

    List<UsersHitsRankingDto> fetchAllRankingEntriesByDay(final Long daysToSubtract) {
        Instant startOfSpecifiedDay = TimeService.getStartOfSpecifiedDay(daysToSubtract);
        Instant endOfSpecifiedDay = TimeService.getEndOfSpecifiedDay(daysToSubtract);
        return UsersHitsRankingMapper.mapToRankingDto(fetchSortedRanking(startOfSpecifiedDay, endOfSpecifiedDay));
    }

    private List<UsersHitsRanking> fetchSortedRanking(Instant start, Instant end) {
        return usersHitsRankingRepository.findByDateBetween(start, end).stream()
                .sorted(Comparator.comparing(UsersHitsRanking::getHits).reversed())
                .collect(Collectors.toList());
    }
}
