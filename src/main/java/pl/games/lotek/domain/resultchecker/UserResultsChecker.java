package pl.games.lotek.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.numbersreceiver.NumbersReceiverFacade;
import pl.games.lotek.domain.numbersreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.util.TimeService;
import pl.games.lotek.domain.winningnumbersgenerator.WinningNumbersGeneratorFacade;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
class UserResultsChecker {


    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;
    private final UserResultsRepository userResultsRepository;
    private final NumbersReceiverFacade numbersReceiverFacade;

    void checkAndSaveResults(String userId) {
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        Set<Integer> winningNumbers = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();

        List<LotekTicketDto> userTicketsDtos = numbersReceiverFacade.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);

        for (LotekTicketDto ticket : userTicketsDtos) {
            boolean alreadyExists = userResultsRepository.existsByUserIdAndUserNumbersId(userId, ticket.id());
            if (alreadyExists) {
                continue;
            }

            Set<Integer> userNumbers = ticket.userNumbers();
            long hits = userNumbers.stream().filter(winningNumbers::contains).count();

            UserResults result = new UserResults(
                    userId,
                    ticket.id(),
                    ticket.userNumbers(),
                    ticket.date().toInstant(),
                    winningNumbers,
                    (int) hits
            );
            userResultsRepository.save(result);
        }
    }
}

