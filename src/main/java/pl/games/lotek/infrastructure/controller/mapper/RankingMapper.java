package pl.games.lotek.infrastructure.controller.mapper;

import pl.games.lotek.infrastructure.controller.dto.RankingDto;
import pl.games.lotek.domain.model.RankingEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RankingMapper {

    public static List<RankingDto> mapToRankingDto(List<RankingEntity> ranking) {
        return ranking.stream()
                .map(RankingEntity -> new RankingDto(
                        RankingEntity.getUserId(),
                        RankingEntity.getDate(),
                        RankingEntity.getHits()))
                .collect(Collectors.toList());
    }

}
