package pl.games.guessnumber.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.SavedNumberDto;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
class NumberSaver {

    private final UserNumberRepository repository;
    private final NumberValidator numberValidator;

    SavedNumberDto saveNumber(String userId, final NumberRequestDto requestDto) {
        numberValidator.validateNumber(requestDto);
        UserNumber userNumber = new UserNumber(userId, LocalDateTime.now().toInstant(ZoneOffset.UTC), requestDto.number());
        UserNumber savedNumber = repository.save(userNumber);
        SavedNumberDto dto = new SavedNumberDto(savedNumber.getId(), savedNumber.getUserId(), savedNumber.getDate(), savedNumber.getNumber());
        return dto;
    }
}
