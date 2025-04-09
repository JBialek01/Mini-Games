package pl.games.lotek.domain.winningnumbersgenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    private final WinningNumbersGenerator winningNumbersGenerator;

    public Set<Integer> getWinningNumbersForYesterday() {
        return winningNumbersGenerator.getWinningNumbersForYesterday();
    }
}
