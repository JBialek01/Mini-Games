package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.WinningNumberDto;
import pl.games.guessnumber.domain.util.GuessNumberConstants;
import pl.games.guessnumber.domain.util.TimeService;

import java.time.Instant;
import java.util.Random;

@Service
@AllArgsConstructor
class WinningNumberProvider {

    private final WinningNumberRepository repository;

    WinningNumberDto getWinningNumber(Long daysToSubstract) {
        Random rand = new Random();
        Integer number = rand.nextInt(GuessNumberConstants.LOWEST_NUMBER, GuessNumberConstants.HIGHEST_NUMBER);

        Instant startOfSpecificDay = TimeService.getStartOfSpecifiedDay(daysToSubstract);
        Instant endOfSpecificDay = TimeService.getEndOfSpecifiedDay(daysToSubstract);

        WinningNumber existingRecord = repository.findByDateBetween(startOfSpecificDay, endOfSpecificDay);
        if (existingRecord != null) {
            return new WinningNumberDto(existingRecord.getId(), existingRecord.getDate(), existingRecord.getNumber());
        }

        repository.save(new WinningNumber(number, Instant.now()));
        WinningNumber savedRecord = repository.findByDateBetween(startOfSpecificDay, endOfSpecificDay);
        return new WinningNumberDto(savedRecord.getId(), savedRecord.getDate(), savedRecord.getNumber());
    }
}
