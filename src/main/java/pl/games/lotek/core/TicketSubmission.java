package pl.games.lotek.core;

import java.util.Set;

public record TicketSubmission(Set<Integer> numbers, String message) {
}
