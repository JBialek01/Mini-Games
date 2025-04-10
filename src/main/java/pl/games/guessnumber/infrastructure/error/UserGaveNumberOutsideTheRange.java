package pl.games.guessnumber.infrastructure.error;

public class UserGaveNumberOutsideTheRange extends RuntimeException {
    public UserGaveNumberOutsideTheRange(String message) {
        super(message);
    }
}
