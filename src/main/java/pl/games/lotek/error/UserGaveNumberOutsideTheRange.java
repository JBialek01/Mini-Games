package pl.games.lotek.error;

public class UserGaveNumberOutsideTheRange extends RuntimeException {
    public UserGaveNumberOutsideTheRange(String message) {
        super(message);
    }
}
