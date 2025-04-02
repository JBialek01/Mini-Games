package pl.games.lotek.infrastructure.error;

public class UserGaveRepeatedNumbers extends RuntimeException {
    public UserGaveRepeatedNumbers(String message) {
        super(message);
    }
}
