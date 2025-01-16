package web;
import app.lotek.UserNumbersProviderInterface;

import java.util.Set;
import java.util.TreeSet;

public class LotekUserNumberWebProvider implements UserNumbersProviderInterface {

    private final Set<Integer> numbers;


    public LotekUserNumberWebProvider(int number1, int number2, int number3, int number4, int number5, int number6) {
        this.numbers = new TreeSet<>();
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);
        numbers.add(number5);
        numbers.add(number6);
    }

    @Override
    public Set<Integer> returnUserNumbers() {
        return numbers;
    }
}
