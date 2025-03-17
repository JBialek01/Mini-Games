package pl.games.lotek.infrastructure.controller.mapper;

import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.domain.model.CheckWinEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CheckWinMapper {

    public static List<CheckWinDto> mapToCheckWin(List<CheckWinEntity> previousDayResults) {
        return previousDayResults.stream()
                .map(checkWinEntity -> new CheckWinDto(
                                checkWinEntity.getUserId(),
                                checkWinEntity.getDate(),
                                checkWinEntity.getUserNumbers(),
                                checkWinEntity.getWinningNumbers(),
                                checkWinEntity.getHits()))
                .collect(Collectors.toList());
    }
}
