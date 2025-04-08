package pl.games.lotek.domain.usershitsranking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class RankingEntriesSaver {

    private final UsersHitsRankingRepository usersHitsRankingRepository;

    void saveNewRankingEntries(Map<String, Integer> userBestHits, Instant start, Instant end) {
        List<UsersHitsRanking> ranking = userBestHits.entrySet().stream()
                .filter(entry -> usersHitsRankingRepository.findByDateBetweenAndUserId(start, end, entry.getKey()).isEmpty())
                .map(entry -> new UsersHitsRanking(ZonedDateTime.now().minusDays(1), entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
        usersHitsRankingRepository.saveAll(ranking);
    }
}
