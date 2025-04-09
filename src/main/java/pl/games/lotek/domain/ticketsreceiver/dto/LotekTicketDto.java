package pl.games.lotek.domain.ticketsreceiver.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
public record LotekTicketDto(String id, String userId, ZonedDateTime date, Set<Integer> userNumbers) {
}
