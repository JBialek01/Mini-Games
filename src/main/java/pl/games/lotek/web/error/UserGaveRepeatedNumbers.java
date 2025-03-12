package pl.games.lotek.web.error;

public class UserGaveRepeatedNumbers extends RuntimeException {
    public UserGaveRepeatedNumbers(String message) {
        super(message);
    }
}
