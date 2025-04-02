package pl.games.lotek.domain.rankinggenerator.dto;

import java.time.ZonedDateTime;

public record UsersHitsRankingDto(String userId, ZonedDateTime date, Integer hits) {
}
