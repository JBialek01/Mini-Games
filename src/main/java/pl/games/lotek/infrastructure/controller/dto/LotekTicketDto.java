package pl.games.lotek.infrastructure.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

public record LotekTicketDto(String id, String userId, ZonedDateTime date, Set<Integer> userNumbers) {
}
