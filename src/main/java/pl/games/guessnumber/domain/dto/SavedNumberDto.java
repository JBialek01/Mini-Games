package pl.games.guessnumber.domain.dto;

import java.time.Instant;

public record SavedNumberDto(String id, String userId, Instant date, Integer number) {
}
