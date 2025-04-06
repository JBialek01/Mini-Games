package pl.games.lotek.domain.usersHitsRanking.dto;

import java.time.ZonedDateTime;

public record UsersHitsRankingDto(String userId, ZonedDateTime date, Integer hits) {
}
