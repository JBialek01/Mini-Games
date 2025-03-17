package pl.games.lotek.infrastructure.controller.dto;

import java.util.Set;

public record TicketSubmissionDto(Set<Integer> numbers, String message) {
}
