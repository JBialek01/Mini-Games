package pl.games.lotek.infrastructure.error;

public class UserGaveNumberOutsideTheRange extends RuntimeException {
    public UserGaveNumberOutsideTheRange(String message) {
        super(message);
    }
}
