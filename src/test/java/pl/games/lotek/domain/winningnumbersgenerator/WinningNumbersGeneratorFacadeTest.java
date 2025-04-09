package pl.games.lotek.domain.winningnumbersgenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersGeneratorFacadeTest {

    private final WinningNumbersRepository winningNumbersRepository = new WinningNumbersRepositoryTestImpl();

    @Test
    @DisplayName("It should generate winning numbers for yesterday")
    public void it_should_generate_winning_numbers_for_yesterday() {
        // given
        WinningNumbersGeneratorFacade winningNumbersGeneratorFacade = new WinningNumbersGeneratorFacadeConfiguration()
                .winningNumbersGeneratorFacade(winningNumbersRepository);
        // when
        Set<Integer> winningNumbers = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();
        // then
        assertThat(winningNumbers.size()).isEqualTo(6);
        assertTrue(winningNumbers.stream().allMatch(number -> number >= 1 && number <= 99));
    }

    @Test
    @DisplayName("It should return same set of numbers if numbers generation method was invoked few times")
    public void it_should_return_same_set_of_numbers_if_numbers_generation_method_was_invoked_few_times() {
        // given
        WinningNumbersGeneratorFacade winningNumbersGeneratorFacade = new WinningNumbersGeneratorFacadeConfiguration()
                .winningNumbersGeneratorFacade(winningNumbersRepository);
        // when
        Set<Integer> winningNumbers = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();
        Set<Integer> winningNumbers2 = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();
        Set<Integer> winningNumbers3 = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();
        // then
        assertThat(winningNumbers.size()).isEqualTo(6);
        assertTrue(winningNumbers.stream().allMatch(number -> number >= 1 && number <= 99));
        assertThat(winningNumbers).isEqualTo(winningNumbers2).isEqualTo(winningNumbers3);
    }

}