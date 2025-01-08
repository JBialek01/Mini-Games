package app;

class GameTestImpl implements Game {

    public String name;

    public GameTestImpl(String name) {
        this.name = name;
    }

    @Override
    public String startGame() {
        System.out.println("Started test game");
        return "";
    }

    @Override
    public String getName() {
        return this.name;
    }


}
