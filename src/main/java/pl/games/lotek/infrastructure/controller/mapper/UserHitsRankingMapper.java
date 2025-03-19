package pl.games.lotek.infrastructure.controller.mapper;

import pl.games.lotek.infrastructure.controller.dto.UserHitsRankingDto;
import pl.games.lotek.domain.model.UserHitsRankingEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserHitsRankingMapper {

    public static List<UserHitsRankingDto> mapToRankingDto(List<UserHitsRankingEntity> ranking) {
        return ranking.stream()
                .map(UserHitsRankingEntity -> new UserHitsRankingDto(
                        UserHitsRankingEntity.getUserId(),
                        UserHitsRankingEntity.getDate(),
                        UserHitsRankingEntity.getHits()))
                .collect(Collectors.toList());
    }

}
