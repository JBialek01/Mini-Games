package pl.games.lotek.error;

import org.springframework.http.HttpStatus;

public record RequestErrorResponseDto(String message, HttpStatus httpStatus) {
}
