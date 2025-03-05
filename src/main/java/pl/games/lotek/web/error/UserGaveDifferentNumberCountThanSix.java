package pl.games.lotek.web.error;

public class UserGaveDifferentNumberCountThanSix extends RuntimeException {
    public UserGaveDifferentNumberCountThanSix(String message) {
        super(message);
    }
}
