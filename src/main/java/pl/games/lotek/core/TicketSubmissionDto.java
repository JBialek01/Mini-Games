package pl.games.lotek.core;

import java.util.Set;

public record TicketSubmissionDto(Set<Integer> numbers, String message) {
}
