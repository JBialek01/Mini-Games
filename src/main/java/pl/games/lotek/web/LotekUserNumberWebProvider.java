package pl.games.lotek.web;

import org.springframework.context.annotation.Scope;
import pl.games.lotek.core.UserNumbersProvider;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
@Scope("prototype")
public class LotekUserNumberWebProvider implements UserNumbersProvider {

    private final Set<Integer> userNumbers = new TreeSet<>();

    public LotekUserNumberWebProvider() {}

    public void addNumber(int number) {
        userNumbers.add(number);
    }

    @Override
    public Set<Integer> returnUserNumbers() {
        return userNumbers;
    }
}

