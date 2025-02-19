package pl.games.lotek.web;

import java.util.Set;

public record LotekGameResponseJson(Set<Integer> userNumbers, Set<Integer> winningNumbers, String message) {
}
