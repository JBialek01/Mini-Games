package app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameSelectorTest {

    @Test
    public void Game_select_test() {
        //given
        GameSelector gameSelector = new GameSelector();
        Mockito.when(gameSelector.selectGame()).thenReturn(1, 0);
        //when
        gameSelector.selectGame();
        //then
        assertEquals(1, gameSelector.selectGame());
    }

}