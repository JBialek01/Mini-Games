//package app.guessnumber;
//
//import app.Game;
//import app.GameResult;
//import app.Nameable;
//
//public class GuessNumber implements Game, Nameable {
//    public static final int LOWEST_NUMBER = 1;
//    public static final int HIGHEST_NUMBER = 1000;
//    UserNumberProviderInterface userNumberProvider;
//    private final WinChecker winChecker;
//    static int winningNumber;
//    String message;
//
//    public GuessNumber(int winningNumber, WinChecker winChecker) {
//        this.winChecker = winChecker;
//        this.winningNumber = winningNumber;
//    }
//
//    @Override
//    public GameResult startGame() {
////        UserNumberProviderInterface userNumberProvider = winChecker.getUserNumberProvider();
//        int userNumber = userNumberProvider.returnUserNumber();
//        message = winChecker.checkWin(winningNumber, userNumber);
//        return new GameResult(null, null, message);
//    }
//
//    @Override
//    public String getName() {
//        return "Guess Number";
//    }
//}
