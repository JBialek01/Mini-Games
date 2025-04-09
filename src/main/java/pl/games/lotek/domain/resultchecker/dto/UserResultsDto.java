package pl.games.lotek.domain.resultchecker.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
public record UserResultsDto(String userId, ZonedDateTime date, Set<Integer> userNumbers, Set<Integer> winningNumbers,Integer hits) {
}
