package pl.games.lotek.core;

import org.springframework.stereotype.Service;
import pl.games.lotek.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CheckWinService {
    private final CheckWinRepository checkWinRepository;
    private final LotekRepository lotekRepository;
    private final WinningNumberRepository winningNumberRepository;

    public CheckWinService(CheckWinRepository checkWinRepository, LotekRepository lotekRepository, WinningNumberRepository winningNumberRepository) {
        this.checkWinRepository = checkWinRepository;
        this.lotekRepository = lotekRepository;
        this.winningNumberRepository = winningNumberRepository;
    }

    public void checkAndSaveResults(String userId) {
        LocalDate previousDay = LocalDate.now().minusDays(1);
        List<String> userTickets = lotekRepository.findByUserIdAndDate(userId, previousDay)
                .stream()
                .map(ticket -> ticket.getUserNumbers())
                .collect(Collectors.toList());

        WinningNumberEntity winningNumbersEntity = winningNumberRepository.findByDate(previousDay);
        if (winningNumbersEntity == null) {
            return;
        }

        Set<Integer> winningNumbers = parseNumbers(winningNumbersEntity.getWinningNumbers());

        for (String ticketNumbers : userTickets) {
            Set<Integer> userNumbers = parseNumbers(ticketNumbers);
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            CheckWinEntity result = new CheckWinEntity(userId, previousDay, ticketNumbers, winningNumbersEntity.getWinningNumbers(), String.valueOf(hits));
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
