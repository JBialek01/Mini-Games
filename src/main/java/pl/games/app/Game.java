package pl.games.app;

public interface Game {
    GameResult startGame();

    //Map<String, LotekEntity> fetchGameHistoryForAllUsers();

    String getName();
}
