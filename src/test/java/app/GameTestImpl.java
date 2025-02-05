package app;

import pl.games.app.Game;
import pl.games.app.GameResult;

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
