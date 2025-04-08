package pl.games.lotek.domain.ticketsreceiver;

import org.springframework.stereotype.Service;
import pl.games.lotek.domain.util.LotekConstants;
import pl.games.lotek.infrastructure.error.UserGaveDifferentNumberCountThanSix;
import pl.games.lotek.infrastructure.error.UserGaveNumberOutsideTheRange;

import java.util.Set;

@Service
class NumbersValidator {

    void validateNumbers(Set<Integer> userNumbers) {
        if (userNumbers.size() != LotekConstants.NUMBERS_COUNT) {
            throw new UserGaveDifferentNumberCountThanSix("You must enter 6 numbers!");
        }
        for (Integer number : userNumbers) {
            if (number < LotekConstants.LOWEST_NUMBER || number > LotekConstants.HIGHEST_NUMBER) {
                throw new UserGaveNumberOutsideTheRange("The numbers entered must be in the range 1-99!");
            }
        }
    }
}
