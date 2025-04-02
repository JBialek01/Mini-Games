package pl.games.lotek.domain.ticketsreceiver;

import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;

import java.util.List;
import java.util.stream.Collectors;

class LotekTicketMapper {

    static List<LotekTicketDto> mapToLotekTicketDto(List<LotekTicket> lotekTicketEntities) {
        return lotekTicketEntities.stream()
                .map(lotekTicketEntity -> new LotekTicketDto(
                        lotekTicketEntity.getId(),
                        lotekTicketEntity.getUserId(),
                        lotekTicketEntity.getDate(),
                        lotekTicketEntity.getUserNumbers()))
                .collect(Collectors.toList());
    }
}
