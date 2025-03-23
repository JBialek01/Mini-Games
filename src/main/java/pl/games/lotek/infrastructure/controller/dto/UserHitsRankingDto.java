package pl.games.lotek.infrastructure.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record UserHitsRankingDto(String userId, ZonedDateTime date, Integer hits) {
}
