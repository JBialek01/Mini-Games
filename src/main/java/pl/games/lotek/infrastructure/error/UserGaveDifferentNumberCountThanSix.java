package pl.games.lotek.infrastructure.error;

public class UserGaveDifferentNumberCountThanSix extends RuntimeException {
    public UserGaveDifferentNumberCountThanSix(String message) {
        super(message);
    }
}
