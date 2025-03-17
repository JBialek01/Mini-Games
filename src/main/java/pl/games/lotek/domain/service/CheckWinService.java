package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.model.WinningNumberEntity;
import pl.games.lotek.domain.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CheckWinService {

    private final CheckWinRepository checkWinRepository;
    private final LotekRepository lotekRepository;
    private final WinningNumberRepository winningNumberRepository;

    public void checkAndSaveResults(String userId) {
        LocalDate previousDay = LocalDate.now().minusDays(1);
        List<LotekTicketEntity> userTickets = lotekRepository.findByUserIdAndDate(userId, previousDay);
        if (userTickets.isEmpty()) {
            return;
        }
        WinningNumberEntity winningNumbersEntity = winningNumberRepository.findByDate(previousDay);

        Set<Integer> winningNumbers = winningNumbersEntity.getWinningNumbers();

        for (LotekTicketEntity ticket : userTickets) {
            // Sprawdzenie, czy wynik już istnieje
            boolean alreadyExists = !checkWinRepository.findByUserIdAndUserNumbersIdAndDate(userId, ticket.getId(), previousDay).isEmpty();
            if (alreadyExists) {
                continue; // Jeśli wpis już istnieje, nie zapisujemy ponownie
            }

            Set<Integer> userNumbers = ticket.getUserNumbers();
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            CheckWinEntity result = new CheckWinEntity(
                    userId,
                    ticket.getId(),
                    ticket.getUserNumbers(),
                    previousDay,
                    winningNumbersEntity.getWinningNumbers(),
                    (int) hits
            );
            checkWinRepository.save(result);
        }
    }
}
