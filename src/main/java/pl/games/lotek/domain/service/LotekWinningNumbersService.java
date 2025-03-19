package pl.games.lotek.domain.service;

import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.LotekConstants;
import pl.games.lotek.domain.model.WinningNumbersEntity;
import pl.games.lotek.domain.repository.WinningNumbersRepository;

import java.time.LocalDate;
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
        LocalDate yesterday = LocalDate.now().minusDays(1);
        WinningNumbersEntity existingRecord = winningNumbersRepository.findByDate(yesterday);
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