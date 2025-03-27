package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.repository.*;

import java.time.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserHitsRankingService {

    private final CheckWinRepository checkWinRepository;
    private final UserHitsRankingRepository userHitsRankingRepository;
    private final LotekTicketRepository lotekTicketRepository;
    private final CheckWinService checkWinService;


    public List<UserHitsRankingEntity> generateRanking() {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        processUserTickets(startOfPreviousDay, endOfPreviousDay);
        List<CheckWinEntity> previousDayResults = checkWinRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        Map<String, Integer> userBestHits = calculateUserBestHits(previousDayResults);
        saveNewRankingEntries(userBestHits, startOfPreviousDay, endOfPreviousDay);
        return fetchSortedRanking(startOfPreviousDay, endOfPreviousDay);
    }

    private void processUserTickets(Instant start, Instant end) {
        List<LotekTicketEntity> userTickets = lotekTicketRepository.findByDateBetween(start, end);
        Set<String> userIds = userTickets.stream().map(LotekTicketEntity::getUserId).collect(Collectors.toSet());
        userIds.forEach(checkWinService::checkAndSaveResults);
    }

    private Map<String, Integer> calculateUserBestHits(List<CheckWinEntity> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        CheckWinEntity::getUserId,
                        CheckWinEntity::getHits,
                        Integer::max));
    }

    private void saveNewRankingEntries(Map<String, Integer> userBestHits, Instant start, Instant end) {
        List<UserHitsRankingEntity> ranking = userBestHits.entrySet().stream()
                .filter(entry -> userHitsRankingRepository.findByDateBetweenAndUserId(start, end, entry.getKey()).isEmpty())
                .map(entry -> new UserHitsRankingEntity(ZonedDateTime.now().minusDays(1), entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
        userHitsRankingRepository.saveAll(ranking);
    }

    private List<UserHitsRankingEntity> fetchSortedRanking(Instant start, Instant end) {
        return userHitsRankingRepository.findByDateBetween(start, end).stream()
                .sorted(Comparator.comparing(UserHitsRankingEntity::getHits).reversed())
                .collect(Collectors.toList());
    }
}
