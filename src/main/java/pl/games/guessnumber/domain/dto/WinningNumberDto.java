package pl.games.guessnumber.domain.dto;

import java.time.Instant;

public record WinningNumberDto(String id, Instant date, Integer number) {
}
