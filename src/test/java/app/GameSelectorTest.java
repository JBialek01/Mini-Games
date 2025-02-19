package app;

import pl.games.app.core.Game;
import pl.games.app.console.GameSelector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSelectorTest {

    @Test
    public void Game_select_test_if_no_games() {
        //given
        Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.nextInt()).thenReturn(1);
        GameSelector gameSelector = new GameSelector(List.of(), scannerMock);
        //when
        int i = gameSelector.selectGame();
        //then
        assertThat(i).isEqualTo(-1);
    }

    @Test
    public void Game_select_test_if_one_game_is_available_and_selected_by_user() {
        //given
        Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.nextInt()).thenReturn(1, 0);
        Game testGame = new GameTestImpl("Gumowe Ucho");
        GameSelector gameSelector = new GameSelector(List.of(testGame), scannerMock);
        //when
        int i = gameSelector.selectGame();
        //then
        assertThat(i).isEqualTo(0);
    }

    @Test
    public void Game_select_two_games () {
        //given
        Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.nextInt()).thenReturn(1, 2, 0);
        Game testGame = new GameTestImpl("Gumowe Ucho");
        Game testGame2 = new GameTestImpl("Gumowe Ucho 2");
        GameSelector gameSelector = new GameSelector(List.of(testGame, testGame2), scannerMock);
        //when
        int i = gameSelector.selectGame();
        //then
        assertThat(i).isEqualTo(0);
    }
}