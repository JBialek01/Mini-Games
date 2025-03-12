package pl.games.lotek.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.repository.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RankingService {

    private final CheckWinRepository checkWinRepository;
    private final RankingRepository rankingRepository;
    private final LotekRepository lotekRepository;
    private final CheckWinService checkWinService;

    public List<RankingEntity> generateRanking() {
        LocalDate previousDay = LocalDate.now().minusDays(1);

        List<LotekTicketEntity> userTickets = lotekRepository.findByDate(previousDay);
        Set<String> userIds = userTickets.stream().map(LotekTicketEntity::getUserId).collect(Collectors.toSet());

        for (String userId : userIds) {
            checkWinService.checkAndSaveResults(userId);
        }

        List<CheckWinEntity> previousDayResults = checkWinRepository.findByDate(previousDay);

        Map<String, String> userBestHits = previousDayResults.stream()
                .collect(Collectors.toMap(
                        CheckWinEntity::getUserId,
                        CheckWinEntity::getHits,
                        (existing, replacement) -> Integer.parseInt(existing) > Integer.parseInt(replacement) ? existing : replacement));

        List<RankingEntity> ranking = userBestHits.entrySet().stream()
                .filter(entry -> rankingRepository.findByDateAndUserId(previousDay, entry.getKey()).isEmpty())
                .map(entry -> new RankingEntity(previousDay, entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        rankingRepository.saveAll(ranking);
        return rankingRepository.findByDate(previousDay).stream()
                .sorted(Comparator.comparing(RankingEntity::getHits).reversed())
                .collect(Collectors.toList());
    }
}
