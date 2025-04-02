package pl.games.lotek.domain.winningnumbersgenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.util.LotekConstants;
import pl.games.lotek.domain.util.TimeService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@Service
@AllArgsConstructor
class WinningNumbersGenerator {

    private final WinningNumbersRepository winningNumbersRepository;

    Set<Integer> getWinningNumbersForYesterday() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        WinningNumbers existingRecord = winningNumbersRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        if (existingRecord != null) {
            return existingRecord.getWinningNumbers();
        }
        Set<Integer> newWinningNumbers = generateWinningNumbers();
        WinningNumbers newEntry = new WinningNumbers(yesterday, newWinningNumbers);
        winningNumbersRepository.save(newEntry);
        return newWinningNumbers;
    }

    private Set<Integer> generateWinningNumbers() {
        Random rand = new Random();
        Set<Integer> numbers = new TreeSet<>();
        while (numbers.size() < LotekConstants.NUMBERS_COUNT) {
            numbers.add(rand.nextInt(LotekConstants.LOWEST_NUMBER, LotekConstants.HIGHEST_NUMBER));
        }
        return numbers;
    }
}