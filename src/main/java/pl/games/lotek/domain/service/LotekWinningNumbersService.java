package pl.games.lotek.domain.service;

import org.springframework.stereotype.Service;
import pl.games.lotek.domain.model.LotekConstants;
import pl.games.lotek.domain.model.WinningNumberEntity;
import pl.games.lotek.domain.repository.WinningNumberRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@Service
public class LotekWinningNumbersService {

    private final WinningNumberRepository winningNumberRepository;

    public LotekWinningNumbersService(WinningNumberRepository winningNumberRepository) {
        this.winningNumberRepository = winningNumberRepository;
    }

    public Set<Integer> getWinningNumbersForYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        WinningNumberEntity existingRecord = winningNumberRepository.findByDate(yesterday);

        if (existingRecord != null) {
            return existingRecord.getWinningNumbers();
        }

        Set<Integer> newWinningNumbers = generateWinningNumbers();
        WinningNumberEntity newEntry = new WinningNumberEntity(yesterday, newWinningNumbers);
        winningNumberRepository.save(newEntry);
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

    private Set<Integer> parseNumbers(String numbersString) {
        Set<Integer> numbers = new TreeSet<>();
        for (String num : numbersString.replace("[", "").replace("]", "").split(", ")) {
            numbers.add(Integer.parseInt(num));
        }
        return numbers;
    }
}