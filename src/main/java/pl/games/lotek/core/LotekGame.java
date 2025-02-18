package pl.games.lotek.core;

import org.springframework.context.annotation.Scope;
import pl.games.app.core.Game;
import pl.games.app.core.GameResult;
import pl.games.app.core.Nameable;
import org.springframework.stereotype.Component;
import pl.games.lotek.repository.LotekEntity;
import pl.games.lotek.repository.LotekRepository;
import pl.games.lotek.web.LotekUserNumberWebProvider;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
@Scope("prototype")
public class LotekGame implements Game, Nameable {
    public static final int LOWEST_NUMBER = 1;
    public static final int HIGHEST_NUMBER = 99;
    public static final int NUMBERS_COUNT = 6;
    private final UserNumbersProvider userNumbersProvider;
    private final LotekRandomNumbersProvider randomNumbersProvider;
    private final LotekRepository lotekRepository;
    private final LotekResultShower resultShower = new LotekResultShower();
    Set<Integer> userNumbers = new TreeSet<>();
    Set<Integer> winningNumbers = new TreeSet<>();
    String message;
    //LotekRepository lotekRepository = new LotekRepository();

    public LotekGame(UserNumbersProvider userNumbersProvider, LotekRandomNumbersProvider randomNumbersProvider, LotekRepository lotekRepository) {
        this.userNumbersProvider = userNumbersProvider;
        this.randomNumbersProvider = randomNumbersProvider;
        this.lotekRepository = lotekRepository;
    }

    @Override
    public GameResult startGame() {
        userNumbers = userNumbersProvider.returnUserNumbers();
        winningNumbers = randomNumbersProvider.returnWinningNumbers();
        String message = lotekWinChecker.checkResults(userNumbers, winningNumbers);
        LotekEntity lotekEntity = new LotekEntity(userNumbers.toString(), winningNumbers.toString());
        lotekRepository.save(lotekEntity);
        return new GameResult(userNumbers, winningNumbers, message);
    }

    public Map<String, LotekEntity> fetchGameHistoryForAllUsers(){
        return lotekRepository.fetchAll();
    }


    @Override
    public String getName() {
        return "Lotto";
    }

    private final LotekWinChecker lotekWinChecker = new LotekWinChecker(new LotekUserNumbersProvider());

    public LotekUserNumberWebProvider getUserNumbersProvider() {
        return (LotekUserNumberWebProvider) userNumbersProvider;
    }
}
