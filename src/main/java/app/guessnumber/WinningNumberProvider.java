package app.guessnumber;

import java.util.Random;

public class WinningNumberProvider {
    int winningNumber;

    public int returnWinningNumber(){
        Random rand = new Random();
        winningNumber = rand.nextInt(GuessNumber.LOWEST_NUMBER, GuessNumber.HIGHEST_NUMBER);
        return winningNumber;
    }
}
