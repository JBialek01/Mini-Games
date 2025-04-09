package pl.games.lotek.domain.usershitsranking.dto;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record UsersHitsRankingDto(String userId, ZonedDateTime date, Integer hits) {
}
