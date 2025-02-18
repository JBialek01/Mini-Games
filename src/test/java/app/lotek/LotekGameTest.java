package app.lotek;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.games.lotek.core.LotekGame;
import pl.games.lotek.core.LotekRandomNumbersProvider;
import pl.games.lotek.core.LotekUserNumbersProvider;
import pl.games.lotek.core.LotekWinChecker;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class LotekGameTest {

//    @Test
//    public void User_entered_correct_numbers(){
//        //given
//        LotekUserNumbersProvider userNumbersProvider = mock(LotekUserNumbersProvider.class);
//        Mockito.when(userNumbersProvider.returnUserNumbers()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
//        LotekWinChecker winChecker = new LotekWinChecker();
//        LotekRandomNumbersProvider winningNumbersProvider = mock(LotekRandomNumbersProvider.class);
//        Mockito.when(winningNumbersProvider.returnWinningNumbers()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
//        LotekGame lotekGame = new LotekGame(userNumbersProvider, winningNumbersProvider);
//        //when
//        String message = lotekGame.startGame(false);
//
//        //then
//        assertThat(message).isEqualTo("\nGratulacje! Wygrałeś");
//    }
//
//    @Test
//    public void user_entered_two_same_numbers(){
//        //given
//        LotekUserNumbersProvider userNumbersProvider = mock(LotekUserNumbersProvider.class);
//        Mockito.when(userNumbersProvider.returnUserNumbers()).thenReturn(Set.of(1, 1, 3, 4, 5, 6));
//        LotekWinChecker winChecker = new LotekWinChecker();
//        LotekRandomNumbersProvider winningNumbersProvider = mock(LotekRandomNumbersProvider.class);
//        Mockito.when(winningNumbersProvider.returnWinningNumbers()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
//        LotekGame lotekGame = new LotekGame(userNumbersProvider, winningNumbersProvider);
//        //when
//        String message = lotekGame.startGame(false);
//
//        //then
//        assertThat(message).isEqualTo("\nGratulacje! Wygrałeś");
//    }
//
//    @Test
//    public void User_numbers_incorrect() {
//        //given
//        LotekUserNumbersProvider userNumbersProvider = mock(LotekUserNumbersProvider.class);
//        Mockito.when(userNumbersProvider.returnUserNumbers()).thenReturn(Set.of(21, 23, 43, 74, 85, 16));
//        LotekWinChecker winChecker = new LotekWinChecker();
//        LotekRandomNumbersProvider winningNumbersProvider = mock(LotekRandomNumbersProvider.class);
//        Mockito.when(winningNumbersProvider.returnWinningNumbers()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
//        LotekGame lotekGame = new LotekGame(userNumbersProvider, winningNumbersProvider);
//        //when
//        String message = lotekGame.startGame(false);
//        //then
//        assertThat(message).isEqualTo("\nTym razem się nie udało, spróbuj ponownie");
//    }
}