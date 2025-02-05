package pl.games.web;

import pl.games.app.lotek.UserNumbersProviderInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
public class LotekUserNumberWebProvider implements UserNumbersProviderInterface {


    private final static Logger logger = LoggerFactory.getLogger(LotekUserNumberWebProvider.class);
    private final Set<Integer> numbers = new TreeSet<>();

    public LotekUserNumberWebProvider() {
        //this.numbers = new TreeSet<>();
//        numbers.add(number1);
//        numbers.add(number2);
//        numbers.add(number3);
//        numbers.add(number4);
//        numbers.add(number5);
//        numbers.add(number6);
//        if (numbers.size() != 6) {
//            String kalqa = "kalqa";
//            logger.error("User gave less than six numbers, user input was: {} username: {}", numbers, kalqa);
//            throw new UserGaveDifferentNumberCountThanSix("Numbers must be 6 numbers");
//        }
    }

    public void addNumber(int number) {
        numbers.add(number);
    }


    @Override
    public Set<Integer> returnUserNumbers() {
        return numbers;
    }
}
