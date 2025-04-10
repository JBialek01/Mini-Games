package pl.games.lotek.domain.resultchecker.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public record UserResultsDto(String userId, ZonedDateTime date, Set<Integer> userNumbers, Set<Integer> winningNumbers,
                             Integer hits) {
}
