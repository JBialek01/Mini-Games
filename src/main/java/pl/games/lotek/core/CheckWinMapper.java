package pl.games.lotek.core;

import pl.games.lotek.repository.CheckWinEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CheckWinMapper {

    public static List<CheckWin> mapToCheckWin(List<CheckWinEntity> previousDayResults) {
        return previousDayResults.stream()
                .map(checkWinEntity -> new CheckWin(
                                checkWinEntity.getUserId(),
                                checkWinEntity.getUserNumbers(),
                                checkWinEntity.getWinningNumbers(),
                                checkWinEntity.getHits(),
                                checkWinEntity.getDate()))
                .collect(Collectors.toList());
    }
}
