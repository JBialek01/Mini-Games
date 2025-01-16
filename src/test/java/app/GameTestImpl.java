package app;

class GameTestImpl implements Game {

    public String name;

    public GameTestImpl(String name) {
        this.name = name;
    }

    @Override
    public String startGame(boolean isHtml) {
        System.out.println("Started test game");
        return "";
    }

    @Override
    public String getName() {
        return this.name;
    }


}
