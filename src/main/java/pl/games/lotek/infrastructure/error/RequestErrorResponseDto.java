package pl.games.lotek.infrastructure.error;

import org.springframework.http.HttpStatus;

public record RequestErrorResponseDto(String message, HttpStatus httpStatus) {
}
