package pl.games.lotek.web.error;

import org.springframework.http.HttpStatus;

public record RequestErrorResponseDto(String message, HttpStatus httpStatus) {
}
