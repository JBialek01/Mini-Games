package pl.games.lotek.infrastructure.controller.error;

public class UserGaveNumberOutsideTheRange extends RuntimeException {
    public UserGaveNumberOutsideTheRange(String message) {
        super(message);
    }
}
