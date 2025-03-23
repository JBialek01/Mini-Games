package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.repository.*;

import java.time.*;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CheckWinService {

    private final AuthenticatedUserService authenticatedUserService;
    private final LotekWinningNumbersService lotekWinningNumbersService;
    private final CheckWinRepository checkWinRepository;
    private final LotekTicketRepository lotekTicketRepository;

    public List<CheckWinEntity> getCheckWinResults(String userId) {
        checkAndSaveResults(userId);
        Instant startOfPreviousDay = LocalDate.now().minusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfPreviousDay = LocalDate.now().minusDays(1).atTime(23, 59, 59,  999999999).atZone(ZoneOffset.UTC).toInstant();
        return checkWinRepository.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);
    }

    public void checkAndSaveResults(String userId) {
        Instant startOfPreviousDay = LocalDate.now().minusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfPreviousDay = LocalDate.now().minusDays(1).atTime(23, 59, 59,  999999999).atZone(ZoneOffset.UTC).toInstant();
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
                    ticket.getDate().toLocalDateTime(),
                    winningNumbers,
                    (int) hits
            );
            checkWinRepository.save(result);
        }
    }
}

