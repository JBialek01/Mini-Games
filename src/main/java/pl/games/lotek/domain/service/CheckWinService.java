package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.repository.*;

import java.time.*;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CheckWinService {

    private final LotekWinningNumbersService lotekWinningNumbersService;
    private final CheckWinRepository checkWinRepository;
    private final LotekTicketRepository lotekTicketRepository;

    public List<CheckWinEntity> getCheckWinResults(String userId) {
        checkAndSaveResults(userId);
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        return checkWinRepository.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);
    }

    public void checkAndSaveResults(String userId) {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        Set<Integer> winningNumbers = lotekWinningNumbersService.getWinningNumbersForYesterday();

        List<LotekTicketEntity> userTickets = lotekTicketRepository.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);

        for (LotekTicketEntity ticket : userTickets) {
            boolean alreadyExists = checkWinRepository.existsByUserIdAndUserNumbersId(userId, ticket.getId());
            if (alreadyExists) {
                continue;
            }

            Set<Integer> userNumbers = ticket.getUserNumbers();
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            CheckWinEntity result = new CheckWinEntity(
                    userId,
                    ticket.getId(),
                    ticket.getUserNumbers(),
                    ticket.getTimestamp(),
                    winningNumbers,
                    (int) hits
            );
            checkWinRepository.save(result);
        }
    }
}

