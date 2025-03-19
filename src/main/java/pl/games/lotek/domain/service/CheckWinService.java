package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.repository.*;
import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.infrastructure.controller.mapper.CheckWinMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CheckWinService {

    private final AuthenticatedUserService authenticatedUserService;
    private final LotekWinningNumbersService lotekWinningNumbersService;
    private final CheckWinRepository checkWinRepository;
    private final LotekTicketRepository lotekTicketRepository;

    public List<CheckWinDto> getCheckWinResults(String userId) {
        checkAndSaveResults(userId);
        List<CheckWinEntity> previousDayResults = checkWinRepository.findByUserIdAndDate(userId, LocalDate.now().minusDays(1));
        return CheckWinMapper.mapToCheckWin(previousDayResults);
    }

    public void checkAndSaveResults(String userId) {
        LocalDate previousDay = LocalDate.now().minusDays(1);
        Set<Integer> winningNumbers = lotekWinningNumbersService.getWinningNumbersForYesterday();

        List<LotekTicketEntity> userTickets = lotekTicketRepository.findByUserIdAndDate(userId, previousDay);

        for (LotekTicketEntity ticket : userTickets) {
            boolean alreadyExists = checkWinRepository.existsByUserIdAndUserNumbersIdAndDate(userId, ticket.getId(), previousDay);
            if (alreadyExists) {
                continue;
            }

            Set<Integer> userNumbers = ticket.getUserNumbers();
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            CheckWinEntity result = new CheckWinEntity(
                    userId,
                    ticket.getId(),
                    ticket.getUserNumbers(),
                    previousDay,
                    winningNumbers,
                    (int) hits
            );
            checkWinRepository.save(result);
        }
    }
}

