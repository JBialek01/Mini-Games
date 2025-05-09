package pl.games.lotek.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.ticketsreceiver.TicketsReceiverFacade;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.util.TimeService;
import pl.games.lotek.domain.winningnumbersgenerator.WinningNumbersGeneratorFacade;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
class UserResultsChecker {


    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;
    private final UserResultsRepository userResultsRepository;
    private final TicketsReceiverFacade ticketsReceiverFacade;

    List<UserResultsDto> checkAndSaveResults(String userId) {
        List<UserResults> userResults = new ArrayList<>();
        Instant startOfPreviousDay = TimeService.getStartOfPreviousUtcDay();
        Instant endOfPreviousDay = TimeService.getEndOfPreviousUtcDay();
        Set<Integer> winningNumbers = winningNumbersGeneratorFacade.getWinningNumbersForYesterday();

        List<LotekTicketDto> userTicketsDtos = ticketsReceiverFacade.findByUserIdAndDateBetween(userId, startOfPreviousDay, endOfPreviousDay);

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
            UserResults userResult = userResultsRepository.save(result);
            userResults.add(userResult);
        }
        return UserResultsMapper.mapToUserResultsDto(userResults);
    }

    void saveUserResults(final UserResultsDto testResult) {
        UserResults userResult = new UserResults(
                testResult.userId(),
                UUID.randomUUID().toString(),
                testResult.userNumbers(),
                testResult.date().toInstant(),
                testResult.winningNumbers(),
                testResult.hits()
        );
        userResultsRepository.save(userResult);
    }
}

