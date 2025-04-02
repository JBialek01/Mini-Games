package pl.games.lotek.domain.ticketsreceiver.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public record LotekTicketDto(String id, String userId, ZonedDateTime date, Set<Integer> userNumbers) {
}
