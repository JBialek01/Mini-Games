package pl.games.lotek.web.error;

public class UserGaveNumberOutsideTheRange extends RuntimeException {
    public UserGaveNumberOutsideTheRange(String message) {
        super(message);
    }
}
