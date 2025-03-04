package pl.games.lotek.error;

public class UserGaveDifferentNumberCountThanSix extends RuntimeException {
    public UserGaveDifferentNumberCountThanSix(String message) {
        super(message);
    }
}
