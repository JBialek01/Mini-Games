package pl.games.lotek.domain.resultchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.ticketsreceiver.TicketsReceiverFacade;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.winningnumbersgenerator.WinningNumbersGeneratorFacade;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade = mock(WinningNumbersGeneratorFacade.class);
    TicketsReceiverFacade ticketsReceiverFacade = mock(TicketsReceiverFacade.class);

    private final UserResultsRepository userResultsRepository = new UserResultsRepositoryTestImpl();
    ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacade(
            new UserResultsRetriever(userResultsRepository, new UserResultsChecker(winningNumbersGeneratorFacade, userResultsRepository, ticketsReceiverFacade)),
            new UserResultsChecker(winningNumbersGeneratorFacade, userResultsRepository, ticketsReceiverFacade),
            new AuthenticatedUserService());


    OAuth2User user = new DefaultOAuth2User(
            Set.of(),
            Map.of(
                    "sub", "userId",
                    "name", "Test User"
            ),
            "name"
    );

    @Test
    @DisplayName("It should check, save and return results for specified user")
    public void it_should_check_save_and_return_results_for_specified_user() {
        //given
        List<LotekTicketDto> tickets = new ArrayList<>();
        final LotekTicketDto lotekTicketDto = LotekTicketDto.builder()
                .id("123")
                .userId("userId")
                .date(ZonedDateTime.now().minusDays(1))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
        final LotekTicketDto lotekTicketDto2 = LotekTicketDto.builder()
                .id("321")
                .userId("userId")
                .date(ZonedDateTime.now().minusDays(1))
                .userNumbers(Set.of(2, 4, 6, 8, 10, 12))
                .build();
        tickets.add(lotekTicketDto);
        tickets.add(lotekTicketDto2);
        when(ticketsReceiverFacade.findByUserIdAndDateBetween(any(), any(), any())).thenReturn(tickets);
        when(winningNumbersGeneratorFacade.getWinningNumbersForYesterday()).thenReturn(Set.of(1, 2, 3, 4, 5, 7));
        //when
        List<UserResultsDto> userResultsDtos = resultCheckerFacade.checkAndSaveResults("userId");
        //then
        assertThat(userResultsDtos).hasSize(2);
        assertThat(userResultsDtos.get(0).userId()).isEqualTo("userId");
        assertThat(userResultsDtos.get(0).userNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(userResultsDtos.get(0).winningNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 7));
        assertThat(userResultsDtos.get(0).hits()).isEqualTo(5);
        assertThat(userResultsDtos.get(1).userId()).isEqualTo("userId");
        assertThat(userResultsDtos.get(1).userNumbers()).isEqualTo(Set.of(2, 4, 6, 8, 10, 12));
        assertThat(userResultsDtos.get(1).winningNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 7));
        assertThat(userResultsDtos.get(1).hits()).isEqualTo(2);
    }

    @Test
    @DisplayName("It should return list of user results within the range of given dates")
    public void it_should_return_list_of_user_results_within_the_range_of_given_dates() {
        // Given
        List<LotekTicketDto> tickets = new ArrayList<>();
        final LotekTicketDto lotekTicketDto = LotekTicketDto.builder()
                .id("123")
                .userId("userId")
                .date(ZonedDateTime.now().minusDays(1))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 8))
                .build();
        tickets.add(lotekTicketDto);
        when(ticketsReceiverFacade.findByUserIdAndDateBetween(any(), any(), any())).thenReturn(tickets);
        when(winningNumbersGeneratorFacade.getWinningNumbersForYesterday()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
        resultCheckerFacade.checkAndSaveResults("userId");
        // When
        List<UserResultsDto> results = resultCheckerFacade.findByDateBetween(Instant.now().minusSeconds(2000000000), Instant.now().plusSeconds(2000000000));
        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).userId()).isEqualTo("userId");
        assertThat(results.get(0).userNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 8));
        assertThat(results.get(0).winningNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(results.get(0).date()).isBetween(ZonedDateTime.now().minusDays(1).minusSeconds(10), ZonedDateTime.now().minusDays(1));
        assertThat(results.get(0).hits()).isEqualTo(5);
    }

    @Test
    public void it_should_get_results_for_specified_user_and_day() {
        // Given
        List<LotekTicketDto> tickets = new ArrayList<>();
        final LotekTicketDto lotekTicketDto = LotekTicketDto.builder()
                .id("123")
                .userId("userId")
                .date(ZonedDateTime.now().minusDays(5))
                .userNumbers(Set.of(1, 2, 3, 4, 7, 8))
                .build();
        tickets.add(lotekTicketDto);
        when(ticketsReceiverFacade.findByUserIdAndDateBetween(any(), any(), any())).thenReturn(tickets);
        when(winningNumbersGeneratorFacade.getWinningNumbersForYesterday()).thenReturn(Set.of(1, 2, 3, 4, 5, 6));
        resultCheckerFacade.checkAndSaveResults("userId");
        // When
        List<UserResultsDto> results = resultCheckerFacade.getResultsForSpecifiedDay(user, 5L);
        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).userId()).isEqualTo("userId");
        assertThat(results.get(0).userNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 7, 8));
        assertThat(results.get(0).winningNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(results.get(0).date()).isBetween(ZonedDateTime.now().minusDays(5).minusSeconds(10), ZonedDateTime.now().minusDays(5));
        assertThat(results.get(0).hits()).isEqualTo(4);
    }

    @Test
    public void it_should_return_results_for_previous_day_for_specified_user() {
        // Given
        List<LotekTicketDto> tickets = new ArrayList<>();
        final LotekTicketDto lotekTicketDto = LotekTicketDto.builder()
                .id("123")
                .userId("userId")
                .date(ZonedDateTime.now().minusDays(1))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
        tickets.add(lotekTicketDto);
        when(ticketsReceiverFacade.findByUserIdAndDateBetween(any(), any(), any())).thenReturn(tickets);
        when(winningNumbersGeneratorFacade.getWinningNumbersForYesterday()).thenReturn(Set.of(1, 2, 3, 4, 5, 7));
        // When
        List<UserResultsDto> results = resultCheckerFacade.getResultsForPreviousDay(user);
        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).userId()).isEqualTo("userId");
        assertThat(results.get(0).userNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(results.get(0).winningNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 7));
        assertThat(results.get(0).date()).isBetween(ZonedDateTime.now().minusDays(1).minusSeconds(10), ZonedDateTime.now().minusDays(1));
        assertThat(results.get(0).hits()).isEqualTo(5);

    }
}