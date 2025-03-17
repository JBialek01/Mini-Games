package pl.games.lotek.infrastructure.controller.error;

public class UserGaveDifferentNumberCountThanSix extends RuntimeException {
    public UserGaveDifferentNumberCountThanSix(String message) {
        super(message);
    }
}
