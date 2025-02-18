package app.guessnumber;

import pl.games.guessnumber.core.WinChecker;
import pl.games.guessnumber.core.WinningNumberProvider;
import pl.games.guessnumber.console.UserNumberProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class GuessNumberTest {
//    @Test
//    public void User_number_too_high_and_user_left_the_game() {
//        //given
//        UserNumberProvider userNumberProvider = mock(UserNumberProvider.class);
//        Mockito.when(userNumberProvider.returnUserNumber()).thenReturn(2, -1);
//        WinChecker winChecker = new WinChecker(userNumberProvider);
//        WinningNumberProvider winningNumberProvider = mock(WinningNumberProvider.class);
//        Mockito.when(winningNumberProvider.returnWinningNumber()).thenReturn(1);
//        GuessNumber guessNumber = new GuessNumber(winningNumberProvider.returnWinningNumber(), winChecker);
//        //when
//        String message = guessNumber.startGame();
//        //then
//        assertThat(message).isEqualTo("Podana liczba jest za wysoka, wyszedłeś z gry");
//    }
//
//    @Test
//    public void User_number_too_low_and_user_left_the_game() {
//        //given
//        UserNumberProvider userNumberProvider = mock(UserNumberProvider.class);
//        Mockito.when(userNumberProvider.returnUserNumber()).thenReturn(2, -1);
//        WinChecker winChecker = new WinChecker(userNumberProvider);
//        WinningNumberProvider winningNumberProvider = mock(WinningNumberProvider.class);
//        Mockito.when(winningNumberProvider.returnWinningNumber()).thenReturn(87);
//        GuessNumber guessNumber = new GuessNumber(winningNumberProvider.returnWinningNumber(), winChecker);
//        //when
//        String message = guessNumber.startGame();
//        //then
//        assertThat(message).isEqualTo("Podana liczba jest za niska, wyszedłeś z gry");
//    }
//
//    @Test
//    public void User_entered_the_winning_number() {
//        //given
//        UserNumberProvider userNumberProvider = mock(UserNumberProvider.class);
//        Mockito.when(userNumberProvider.returnUserNumber()).thenReturn(55);
//        WinChecker winChecker = new WinChecker(userNumberProvider);
//        WinningNumberProvider winningNumberProvider = mock(WinningNumberProvider.class);
//        Mockito.when(winningNumberProvider.returnWinningNumber()).thenReturn(55);
//        GuessNumber guessNumber = new GuessNumber(winningNumberProvider.returnWinningNumber(), winChecker);
//        //when
//        String message = guessNumber.startGame();
//        //then
//        assertThat(message).isEqualTo("Gratulacje zgadłeś!");
//    }
}