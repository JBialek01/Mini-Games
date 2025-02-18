package pl.games.app.core;

public interface Game {
    GameResult startGame();

    //Map<String, LotekEntity> fetchGameHistoryForAllUsers();

    String getName();
}
