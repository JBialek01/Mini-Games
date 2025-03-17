package pl.games.lotek.infrastructure.controller.dto;

import java.time.LocalDate;
import java.util.Set;

public record LotekTicketDto(String id, String userId, LocalDate date, Set<Integer> userNumbers) {
}
