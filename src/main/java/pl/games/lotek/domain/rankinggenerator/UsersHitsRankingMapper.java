package pl.games.lotek.domain.rankinggenerator;

import pl.games.lotek.domain.rankinggenerator.dto.UsersHitsRankingDto;

import java.util.List;
import java.util.stream.Collectors;

class UsersHitsRankingMapper {

    static List<UsersHitsRankingDto> mapToRankingDto(List<UsersHitsRanking> ranking) {
        return ranking.stream()
                .map(userHitsRankingEntity -> new UsersHitsRankingDto(
                        userHitsRankingEntity.getUserId(),
                        userHitsRankingEntity.getDate(),
                        userHitsRankingEntity.getHits()))
                .collect(Collectors.toList());
    }

}
