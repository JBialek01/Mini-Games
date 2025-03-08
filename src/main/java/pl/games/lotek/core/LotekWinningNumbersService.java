package pl.games.lotek.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.games.lotek.repository.WinningNumberEntity;
import pl.games.lotek.repository.WinningNumberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

@Service
public class LotekWinningNumbersService {

    private final WinningNumberRepository winningNumberRepository;

    public LotekWinningNumbersService(WinningNumberRepository winningNumberRepository) {
        this.winningNumberRepository = winningNumberRepository;
    }

    public Set<Integer> getWinningNumbersForToday() {
        LocalDate today = LocalDate.now();
        WinningNumberEntity existingRecord = winningNumberRepository.findByDate(today);

        if (existingRecord != null) {
            return parseNumbers(existingRecord.getWinningNumbers());
        }

        Set<Integer> newWinningNumbers = generateWinningNumbers();
        WinningNumberEntity newEntry = new WinningNumberEntity(today, newWinningNumbers.toString());
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