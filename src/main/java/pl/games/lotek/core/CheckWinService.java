package pl.games.lotek.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (winningNumbersEntity == null) {
            return;
        }

        Set<Integer> winningNumbers = parseNumbers(winningNumbersEntity.getWinningNumbers());

        for (LotekTicketEntity ticket : userTickets) {
            // Sprawdzenie, czy wynik już istnieje
            boolean alreadyExists = !checkWinRepository.findByUserIdAndUserNumbersIdAndDate(userId, ticket.getId(), previousDay).isEmpty();
            if (alreadyExists) {
                continue; // Jeśli wpis już istnieje, nie zapisujemy ponownie
            }

            Set<Integer> userNumbers = parseNumbers(ticket.getUserNumbers());
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            CheckWinEntity result = new CheckWinEntity(
                    userId,
                    ticket.getId(),
                    ticket.getUserNumbers(),
                    previousDay,
                    winningNumbersEntity.getWinningNumbers(),
                    String.valueOf(hits)
            );
            checkWinRepository.save(result);
        }
    }


    private Set<Integer> parseNumbers(String numbersString) {
        return Set.of(numbersString.replace("[", "").replace("]", "").split(", "))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
