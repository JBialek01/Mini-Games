package pl.games.lotek.infrastructure.controller.dto;

import java.time.LocalDate;
import java.util.Set;


public record CheckWinDto(String userId, Set<Integer> userNumbers, Set<Integer> winningNumbers, Integer hits, LocalDate date) {
    }
