package pl.games.lotek.infrastructure.numbersreceiver.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserNumbersRequestDto(
        @NotNull(message = "numbers cannot be null")
        @NotEmpty(message = "numbers cannot be empty")
        List<Integer> numbers) {

}
