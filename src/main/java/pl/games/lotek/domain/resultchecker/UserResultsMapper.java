package pl.games.lotek.domain.resultchecker;

import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.util.List;
import java.util.stream.Collectors;

class UserResultsMapper {

    static List<UserResultsDto> mapToUserResultsDto(List<UserResults> previousDayResults) {

        return previousDayResults.stream()
                .map(checkWinEntity -> new UserResultsDto(
                        checkWinEntity.getUserId(),
                        checkWinEntity.getDate(),
                        checkWinEntity.getUserNumbers(),
                        checkWinEntity.getWinningNumbers(),
                        checkWinEntity.getHits()))
                .collect(Collectors.toList());
    }
}
