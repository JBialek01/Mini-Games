package pl.games.lotek.domain.numbersreceiver.dto;

import java.util.Set;

public record TicketSubmissionDto(Set<Integer> numbers, String message) {
}
