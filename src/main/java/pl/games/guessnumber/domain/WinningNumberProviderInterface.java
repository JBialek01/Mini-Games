package pl.games.guessnumber.domain;

import pl.games.guessnumber.domain.dto.WinningNumberDto;

interface WinningNumberProviderInterface {
    WinningNumberDto getWinningNumber(Long daysToSubstract);
}
