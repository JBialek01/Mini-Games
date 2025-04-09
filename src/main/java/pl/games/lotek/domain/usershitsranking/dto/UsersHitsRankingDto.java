package pl.games.lotek.domain.usershitsranking.dto;

import java.time.ZonedDateTime;


public record UsersHitsRankingDto(String userId, ZonedDateTime date, Integer hits) {
}
