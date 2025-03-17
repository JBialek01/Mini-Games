package pl.games.lotek.infrastructure.controller.mapper;

import pl.games.lotek.infrastructure.controller.dto.LotekTicketDto;
import pl.games.lotek.domain.model.LotekTicketEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LotekTicketMapper {

    public static List<LotekTicketDto> mapToLotekTicketDto(List<LotekTicketEntity> lotekTicketEntities) {
        return lotekTicketEntities.stream()
                .map(lotekTicketEntity -> new LotekTicketDto(
                        lotekTicketEntity.getId(),
                        lotekTicketEntity.getUserId(),
                        lotekTicketEntity.getDate(),
                        lotekTicketEntity.getUserNumbers()))
                .collect(Collectors.toList());
    }
}
