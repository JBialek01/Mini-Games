package pl.games.guessnumber.domain;

import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.util.GuessNumberConstants;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;
import pl.games.guessnumber.infrastructure.error.UserGaveNumberOutsideTheRange;

@Service
class NumberValidator {
    void validateNumber(NumberRequestDto numberRequestDto) {
        Integer userNumber = numberRequestDto.number();
        if (userNumber < GuessNumberConstants.LOWEST_NUMBER || userNumber > GuessNumberConstants.HIGHEST_NUMBER) {
            throw new UserGaveNumberOutsideTheRange(
                    "Number must be between " + GuessNumberConstants.LOWEST_NUMBER + " and " + GuessNumberConstants.HIGHEST_NUMBER);
        }
    }
}
