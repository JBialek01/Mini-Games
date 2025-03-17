package pl.games.lotek.infrastructure.controller.dto;

import java.time.LocalDate;

public record RankingDto(String userId, LocalDate date, Integer hits) {
}
