package app;

import pl.games.app.core.Game;
import pl.games.app.core.GameResult;

class GameTestImpl implements Game {

    public String name;

    public GameTestImpl(String name) {
        this.name = name;
    }

    @Override
    public GameResult startGame() {
        System.out.println("Started test game");
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
