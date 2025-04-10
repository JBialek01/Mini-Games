package pl.games.guessnumber.domain.dto;

public record UserRankingDto(String userId, Integer numberOfAttempts, String message) {
}
