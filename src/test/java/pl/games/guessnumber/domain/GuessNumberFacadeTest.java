package pl.games.guessnumber.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.games.auth.AuthenticatedUserService;
import pl.games.guessnumber.domain.dto.DrawDateDto;
import pl.games.guessnumber.domain.dto.NumberSubmissionDto;
import pl.games.guessnumber.domain.dto.WinningNumberDto;
import pl.games.guessnumber.infrastructure.controller.NumberRequestDto;
import pl.games.guessnumber.infrastructure.error.UserGaveNumberOutsideTheRange;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GuessNumberFacadeTest {

    GuessNumberFacade guessNumberFacade = GuessNumberFacadeConfiguration.createGuessNumber(
            new UserNumberRepositoryTestImlp(),
            new WinningNumberRepositoryTestImpl(),
            new UserGameSessionRepositoryTestImpl()
    );

    OAuth2User user = new DefaultOAuth2User(
            Set.of(),
            Map.of(
                    "sub", "userId",
                    "name", "Test User"
            ),
            "name"
    );

    @Test
    public void it_should_return_message_that_given_number_was_too_low(){
        // Given
        WinningNumberProviderInterface winningNumberProvider =
                daysToSubstract -> new WinningNumberDto("id", Instant.now(), 10);
        var authenticatedUserService = mock(AuthenticatedUserService.class);
        when(authenticatedUserService.getAuthenticatedUserId(any())).thenReturn("userId");
        var userGameSessionRepository = new UserGameSessionRepositoryTestImpl();
        var resultChecker = new ResultChecker(winningNumberProvider, userGameSessionRepository);
        var numberValidator = new NumberValidator();
        var userNumberRepository = new UserNumberRepositoryTestImlp();
        var userNumberSaver = new UserNumberSaver(userNumberRepository, numberValidator);

        var userResultRetriever = mock(UserResultRetriever.class);
        var drawDateRetriever = mock(DrawDateRetriever.class);
        var userRankingRetriever = mock(UserRankingRetriever.class);
        var guessNumberFacade = new GuessNumberFacade(
                authenticatedUserService,
                userResultRetriever,
                userNumberSaver,
                resultChecker,
                drawDateRetriever,
                userRankingRetriever
        );
        // When
        NumberSubmissionDto response = guessNumberFacade.submitNumber(user, new NumberRequestDto(1));
        // Then
        assertThat(response.message()).isEqualTo("Provided number is too low");
    }

    @Test
    public void it_should_return_message_that_given_number_was_too_high(){
        // Given
        var winningNumberProvider = mock(WinningNumberProvider.class);
        when(winningNumberProvider.getWinningNumber(0L))
                .thenReturn(new WinningNumberDto("id", Instant.now(), 10));
        var authenticatedUserService = mock(AuthenticatedUserService.class);
        when(authenticatedUserService.getAuthenticatedUserId(any())).thenReturn("userId");
        var userGameSessionRepository = new UserGameSessionRepositoryTestImpl();
        var resultChecker = new ResultChecker(winningNumberProvider, userGameSessionRepository);
        var numberValidator = new NumberValidator();
        var userNumberRepository = new UserNumberRepositoryTestImlp();
        var userNumberSaver = new UserNumberSaver(userNumberRepository, numberValidator);

        var userResultRetriever = mock(UserResultRetriever.class);
        var drawDateRetriever = mock(DrawDateRetriever.class);
        var userRankingRetriever = mock(UserRankingRetriever.class);
        var guessNumberFacade = new GuessNumberFacade(
                authenticatedUserService,
                userResultRetriever,
                userNumberSaver,
                resultChecker,
                drawDateRetriever,
                userRankingRetriever
        );
        // When
        NumberSubmissionDto response = guessNumberFacade.submitNumber(user, new NumberRequestDto(20));
        // Then
        assertThat(response.message()).isEqualTo("Provided number is too high");
    }

    @Test
    public void it_should_return_message_that_given_number_was_correct(){
        // Given
        var winningNumberProvider = mock(WinningNumberProvider.class);
        when(winningNumberProvider.getWinningNumber(0L))
                .thenReturn(new WinningNumberDto("id", Instant.now(), 10));
        var authenticatedUserService = mock(AuthenticatedUserService.class);
        when(authenticatedUserService.getAuthenticatedUserId(any())).thenReturn("userId");
        var userGameSessionRepository = new UserGameSessionRepositoryTestImpl();
        var resultChecker = new ResultChecker(winningNumberProvider, userGameSessionRepository);
        var numberValidator = new NumberValidator();
        var userNumberRepository = new UserNumberRepositoryTestImlp();
        var userNumberSaver = new UserNumberSaver(userNumberRepository, numberValidator);

        var userResultRetriever = mock(UserResultRetriever.class);
        var drawDateRetriever = mock(DrawDateRetriever.class);
        var userRankingRetriever = mock(UserRankingRetriever.class);
        var guessNumberFacade = new GuessNumberFacade(
                authenticatedUserService,
                userResultRetriever,
                userNumberSaver,
                resultChecker,
                drawDateRetriever,
                userRankingRetriever
        );
        // When
        NumberSubmissionDto response = guessNumberFacade.submitNumber(user, new NumberRequestDto(10));
        // Then
        assertThat(response.message()).isEqualTo("You guessed it, congratulations!");
    }

    @Test
    public void it_should_throw_UserGaveNumberOutsideTheRange_exception(){
        // Given
        // When
        Throwable throwable = catchThrowable(() -> guessNumberFacade.submitNumber(user, new NumberRequestDto(4243)));
        // Then
        assertThat(throwable).isInstanceOf(UserGaveNumberOutsideTheRange.class);
        assertThat(throwable.getMessage()).isEqualTo("Number must be between 1 and 1000");
    }

    @Test
    public void it_should_return_next_draw_date(){
        // Given
        // When
        DrawDateDto response = guessNumberFacade.getNextDrawDate();
        // Then
        Instant instantDate = LocalDate.now(ZoneOffset.UTC).plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instantDate, ZoneOffset.systemDefault());
        assertThat(response.date()).isEqualTo(dateTime);
    }
}