package pl.games.lotek.domain.service;

import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.LotekConstants;
import pl.games.lotek.domain.model.WinningNumbersEntity;
import pl.games.lotek.domain.repository.WinningNumbersRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@Service
public class LotekWinningNumbersService {

    private final WinningNumbersRepository winningNumbersRepository;

    public LotekWinningNumbersService(WinningNumbersRepository winningNumbersRepository) {
        this.winningNumbersRepository = winningNumbersRepository;
    }

    public Set<Integer> getWinningNumbersForYesterday() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        Instant startOfPreviousDay = LocalDate.now().minusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfPreviousDay = LocalDate.now().minusDays(1).atTime(23, 59, 59,  999999999).atZone(ZoneOffset.UTC).toInstant();
        WinningNumbersEntity existingRecord = winningNumbersRepository.findByDateBetween(startOfPreviousDay, endOfPreviousDay);
        if (existingRecord != null) {
            return existingRecord.getWinningNumbers();
        }
        Set<Integer> newWinningNumbers = generateWinningNumbers();
        WinningNumbersEntity newEntry = new WinningNumbersEntity(yesterday, newWinningNumbers);
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