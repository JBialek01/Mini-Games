package pl.games.web;

class UserGaveDifferentNumberCountThanSix extends RuntimeException {
    public UserGaveDifferentNumberCountThanSix(String message) {
        super(message);
    }
}
