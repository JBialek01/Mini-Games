package app.guessnumber;

import java.util.Random;

public class WinningNumberProvider {
        public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 1000;
    int winningNumber;

    public int returnWinningNumber(){
        Random rand = new Random();
        winningNumber = rand.nextInt(LOWEST_NUMBER, HIGHEST_NUMBER);
        return winningNumber;
    }
}
