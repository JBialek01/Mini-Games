package web;

import app.lotek.UserNumbersProviderInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;


public class LotekUserNumberWebProvider implements UserNumbersProviderInterface {

    private final static Logger logger = LoggerFactory.getLogger(LotekUserNumberWebProvider.class);
    private final Set<Integer> numbers;

    public LotekUserNumberWebProvider(int number1, int number2, int number3, int number4, int number5, int number6) {
        this.numbers = new TreeSet<>();
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);
        numbers.add(number5);
        numbers.add(number6);
        if (numbers.size() != 6) {
            String kalqa = "kalqa";
            logger.error("User gave less than six numbers, user input was: {} username: {}", numbers, kalqa);
            throw new UserGaveDifferentNumberCountThanSix("Numbers must be 6 numbers");
        }
    }

    @Override
    public Set<Integer> returnUserNumbers() {
        return numbers;
    }
}
