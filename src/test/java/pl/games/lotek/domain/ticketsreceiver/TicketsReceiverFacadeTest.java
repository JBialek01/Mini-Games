package pl.games.lotek.domain.ticketsreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.ticketsreceiver.dto.LotekTicketDto;
import pl.games.lotek.domain.ticketsreceiver.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.error.UserGaveDifferentNumberCountThanSix;
import pl.games.lotek.infrastructure.error.UserGaveNumberOutsideTheRange;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TicketsReceiverFacadeTest {

    private final LotekTicketRepository ticketRepository = new LotekTicketRepositoryTestImpl();
    TicketsReceiverFacade ticketsReceiverFacade = new TicketsReceiverFacadeConfiguration().ticketsReceiverFacade(ticketRepository);

    OAuth2User user = new DefaultOAuth2User(
            Set.of(),
            Map.of(
                    "sub", "1234567890",
                    "name", "Test User"
            ),
            "name"
    );

    OAuth2User user2 = new DefaultOAuth2User(
            Set.of(),
            Map.of(
                    "sub", "1122334455",
                    "name", "Test User2"
            ),
            "name"
    );

    @Test
    @DisplayName("It should submit ticket")
    public void it_should_submit_ticket() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        // when
        TicketSubmissionDto ticketSubmissionDto = ticketsReceiverFacade.submitTicket(user, numbersFromUser);
        // then
        assertThat(ticketSubmissionDto.numbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(ticketSubmissionDto.message()).isEqualTo("Ticket submitted!");
    }

    @Test
    @DisplayName("It should throw UserGaveDifferentNumberCountThanSix exception")
    public void it_should_throw_user_gave_different_number_count_than_six_exception() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        // when
        Throwable throwable = catchThrowable(() -> ticketsReceiverFacade.submitTicket(user, numbersFromUser));
        // then
        assertThat(throwable).isInstanceOf(UserGaveDifferentNumberCountThanSix.class);
        assertThat(throwable.getMessage()).isEqualTo("You must enter 6 numbers!");
    }

    @Test
    @DisplayName("It should throw UserGaveNumberOutsideTheRange exception")
    public void it_should_throw_user_gave_number_outside_the_range_exception() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 100);
        // when
        Throwable throwable = catchThrowable(() -> ticketsReceiverFacade.submitTicket(user, numbersFromUser));
        // then
        assertThat(throwable).isInstanceOf(UserGaveNumberOutsideTheRange.class);
        assertThat(throwable.getMessage()).isEqualTo("The numbers entered must be in the range 1-99!");
    }

    @Test
    @DisplayName("It should return tickets with dates within given range")
    public void it_should_return_tickets_with_dates_within_given_range() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        Set<Integer> numbersFromUser2 = Set.of(10, 20, 30, 40, 50, 60);
        ticketsReceiverFacade.submitTicket(user, numbersFromUser);
        ticketsReceiverFacade.submitTicket(user2, numbersFromUser2);
        // when
        List<LotekTicketDto> tickets = ticketsReceiverFacade.findByDateBetween(
                Instant.now().minusSeconds(100),
                Instant.now().plusSeconds(100));
        // then
        assertThat(tickets).hasSize(2);
        tickets.stream().map(LotekTicketDto::userNumbers)
                .forEach(numbers -> assertThat(numbers).isIn(Set.of(numbersFromUser, numbersFromUser2)));
    }

    @Test
    @DisplayName("It should return tickets with given userId and dates within given range")
    public void it_should_return_tickets_with_given_userId_and_dates_within_given_range() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        Set<Integer> numbersFromUser2 = Set.of(10, 20, 30, 40, 50, 60);
        AuthenticatedUserService authenticatedUserService = new AuthenticatedUserService();
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        ticketsReceiverFacade.submitTicket(user, numbersFromUser);
        ticketsReceiverFacade.submitTicket(user, numbersFromUser2);
        // when
        List<LotekTicketDto> tickets = ticketsReceiverFacade.findByUserIdAndDateBetween(
                userId,
                Instant.now().minusSeconds(100),
                Instant.now().plusSeconds(100));
        // then
        assertThat(tickets).hasSize(2);
        tickets.stream().map(LotekTicketDto::userId).forEach(ids -> assertThat(ids).isEqualTo(userId));
        tickets.stream().map(LotekTicketDto::userNumbers)
                        .forEach(numbers -> assertThat(numbers).isIn(Set.of(numbersFromUser, numbersFromUser2)));
    }
}