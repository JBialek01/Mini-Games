package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.SavedNumberDto;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
class UserNumberSaver {

    private final UserNumberRepository repository;
    private final NumberValidator numberValidator;

    SavedNumberDto saveNumber(String userId, final NumberRequestDto requestDto) {
        numberValidator.validateNumber(requestDto);
        UserNumber userNumber = new UserNumber(userId, ZonedDateTime.now(ZoneId.systemDefault()).toInstant(), requestDto.number());
        UserNumber savedNumber = repository.save(userNumber);
        SavedNumberDto dto = new SavedNumberDto(savedNumber.getId(), savedNumber.getUserId(), savedNumber.getDate(), savedNumber.getNumber());
        return dto;
    }
}
