package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.repository.*;
import pl.games.lotek.infrastructure.controller.dto.UserHitsRankingDto;
import pl.games.lotek.infrastructure.controller.mapper.UserHitsRankingMapper;

import java.time.LocalDate;
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

    public List<UserHitsRankingDto> generateRanking() {

        LocalDate previousDay = LocalDate.now().minusDays(1);
        List<LotekTicketEntity> userTickets = lotekTicketRepository.findByDate(previousDay);
        Set<String> userIds = userTickets.stream().map(LotekTicketEntity::getUserId).collect(Collectors.toSet());

        for (String userId : userIds) {
            checkWinService.checkAndSaveResults(userId);
        }

        List<CheckWinEntity> previousDayResults = checkWinRepository.findByDate(previousDay);

        Map<String, Integer> userBestHits = previousDayResults.stream()
                .collect(Collectors.toMap(
                        CheckWinEntity::getUserId,
                        CheckWinEntity::getHits,
                        (existing, replacement) -> existing > replacement ? existing : replacement));

        List<UserHitsRankingEntity> ranking = userBestHits.entrySet().stream()
                .filter(entry -> userHitsRankingRepository.findByDateAndUserId(previousDay, entry.getKey()).isEmpty())
                .map(entry -> new UserHitsRankingEntity(previousDay, entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        userHitsRankingRepository.saveAll(ranking);
        List<UserHitsRankingEntity> rankingList = userHitsRankingRepository.findByDate(previousDay).stream()
                .sorted(Comparator.comparing(UserHitsRankingEntity::getHits).reversed())
                .collect(Collectors.toList());
        return UserHitsRankingMapper.mapToRankingDto(rankingList);
    }

}
