package pl.games.lotek.domain.usersHitsRanking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UserBestHitsCalculator {

    Map<String, Integer> calculateUserBestHits(List<UserResultsDto> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        UserResultsDto::userId,
                        UserResultsDto::hits,
                        Integer::max));
    }
}
