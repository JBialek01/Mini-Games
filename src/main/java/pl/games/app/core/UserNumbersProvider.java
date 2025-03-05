package pl.games.app.core;

import java.util.Set;

public interface UserNumbersProvider {
    void addNumbers(Set<Integer> numbers);

    Set<Integer>returnUserNumbers();
}
