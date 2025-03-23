package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.repository.*;
import pl.games.lotek.infrastructure.controller.dto.UserHitsRankingDto;
import pl.games.lotek.infrastructure.controller.mapper.UserHitsRankingMapper;

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
        Instant startOfPreviousDay = LocalDate.now().minusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfPreviousDay = LocalDate.now().minusDays(1).atTime(23, 59, 59,  999999999).atZone(ZoneOffset.UTC).toInstant();

        List<LotekTicketEntity> userTickets = lotekTicketRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        Set<String> userIds = userTickets.stream().map(LotekTicketEntity::getUserId).collect(Collectors.toSet());

        for (String userId : userIds) {
            checkWinService.checkAndSaveResults(userId);
        }

        List<CheckWinEntity> previousDayResults = checkWinRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);

        Map<String, Integer> userBestHits = previousDayResults.stream()
                .collect(Collectors.toMap(
                        CheckWinEntity::getUserId,
                        CheckWinEntity::getHits,
                        (existing, replacement) -> existing > replacement ? existing : replacement));

        List<UserHitsRankingEntity> ranking = userBestHits.entrySet().stream()
                .filter(entry -> userHitsRankingRepository.findByDateBetweenAndUserId(startOfPreviousDay, endOfPreviousDay, entry.getKey()).isEmpty())
                .map(entry -> new UserHitsRankingEntity(LocalDateTime.now().minusDays(1), entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        userHitsRankingRepository.saveAll(ranking);

        return userHitsRankingRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay).stream()
                .sorted(Comparator.comparing(UserHitsRankingEntity::getHits).reversed())
                .collect(Collectors.toList());
    }

}
