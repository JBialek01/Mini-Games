package pl.games.lotek.domain.rankinggenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.numbersreceiver.NumbersReceiverFacade;
import pl.games.lotek.domain.numbersreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AllTicketsChecker {

    private final NumbersReceiverFacade numbersReceiverFacade;
    private final ResultCheckerFacade resultCheckerFacade;

    void checkAllUsersTickets(Instant start, Instant end) {
        List<LotekTicketDto> userTicketsDtos = numbersReceiverFacade.findByDateBetween(start, end);
        Set<String> userIds = userTicketsDtos.stream().map(LotekTicketDto::userId).collect(Collectors.toSet());
        userIds.forEach(resultCheckerFacade::checkAndSaveResults);
    }
}
