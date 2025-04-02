package pl.games.lotek.domain.ticketsreceiver.dto;

import java.util.Set;

public record TicketSubmissionDto(Set<Integer> numbers, String message) {
}
