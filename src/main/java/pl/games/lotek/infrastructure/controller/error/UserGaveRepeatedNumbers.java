package pl.games.lotek.infrastructure.controller.error;

public class UserGaveRepeatedNumbers extends RuntimeException {
    public UserGaveRepeatedNumbers(String message) {
        super(message);
    }
}
