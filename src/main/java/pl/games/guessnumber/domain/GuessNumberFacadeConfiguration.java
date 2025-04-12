package pl.games.guessnumber.domain;

import pl.games.auth.AuthenticatedUserService;

class GuessNumberFacadeConfiguration {
    public static GuessNumberFacade createGuessNumber(final UserNumberRepository userNumberRepository,
                                                      final WinningNumberRepository winningNumberRepository,
                                                      final UserGameSessionRepository userGameSessionRepository) {
        AuthenticatedUserService authenticatedUserService = new AuthenticatedUserService();
        UserResultRetriever userResultRetriever = new UserResultRetriever(userGameSessionRepository);
        NumberValidator numberValidator = new NumberValidator();
        UserNumberSaver userNumberSaver = new UserNumberSaver(userNumberRepository, numberValidator);
        WinningNumberProvider winningNumberProvider = new WinningNumberProvider(winningNumberRepository);
        ResultChecker resultChecker = new ResultChecker(winningNumberProvider, userGameSessionRepository);
        DrawDateRetriever drawDateRetriever = new DrawDateRetriever();
        UserRankingRetriever userRankingRetriever = new UserRankingRetriever(userGameSessionRepository);
        return new GuessNumberFacade(
                authenticatedUserService,
                userResultRetriever,
                userNumberSaver,
                resultChecker,
                drawDateRetriever,
                userRankingRetriever
        );
    }
}
