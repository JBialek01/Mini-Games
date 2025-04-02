package pl.games.lotek.domain.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.games.app.core.UserNumbersProvider;

import java.util.Set;
import java.util.TreeSet;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LotekUserNumbersWebProvider implements UserNumbersProvider {

    private final Set<Integer> userNumbers = new TreeSet<>();

    public LotekUserNumbersWebProvider() {}


    @Override
    public void addNumbers(Set<Integer> numbers) {
        userNumbers.addAll(numbers);
    }

    @Override
    public Set<Integer> returnUserNumbers() {
        return userNumbers;
    }
}

