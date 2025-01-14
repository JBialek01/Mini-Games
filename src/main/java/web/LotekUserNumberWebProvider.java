package web;

import app.guessnumber.UserNumberProviderInterface;
import app.lotek.UserNumbersProviderInterface;

public class LotekUserNumberWebProvider implements UserNumbersProviderInterface {

    int number, number2, number3, number4, number5, number6;

    public LotekUserNumberWebProvider(int number, int number2, int number3, int number4, int number5, int number6) {
        this.number = number;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
    }

    private int[] numbers = {number, number2, number3, number4, number5, number6};

    @Override
    public int[] returnUserNumber() {
        return numbers;
    }
}
