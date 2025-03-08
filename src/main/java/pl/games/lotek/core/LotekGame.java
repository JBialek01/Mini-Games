//package pl.games.lotek.core;
//
//import org.springframework.context.annotation.Scope;
//import pl.games.app.core.Game;
//import pl.games.app.core.GameResult;
//import pl.games.app.core.Nameable;
//import org.springframework.stereotype.Component;
//import pl.games.app.core.UserNumbersProvider;
//import pl.games.lotek.repository.LotekTicketEntity;
//import pl.games.lotek.repository.LotekRepository;
//import pl.games.lotek.web.LotekUserNumberWebProvider;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.*;
//
//@Component
//@Scope("prototype")
//public class LotekGame{
//    public static final int LOWEST_NUMBER = 1;
//    public static final int HIGHEST_NUMBER = 99;
//    public static final int NUMBERS_COUNT = 6;
//    private final UserNumbersProvider userNumbersProvider;
////    private final LotekRandomNumbersProvider randomNumbersProvider;
//    private final LotekRepository lotekRepository;
//    private final LotekWinningNumbersService lotekWinningNumbersService;
////    Set<Integer> userNumbers = new TreeSet<>();
////    Set<Integer> winningNumbers = new TreeSet<>();
////    String message;
//
//    public LotekGame(LotekUserNumberWebProvider userNumbersProvider, LotekWinningNumbersService lotekWinningNumbersService, LotekRepository lotekRepository) {
//        this.userNumbersProvider = userNumbersProvider;
//        this.lotekWinningNumbersService = lotekWinningNumbersService;
//        this.lotekRepository = lotekRepository;
//    }
//
//    public TicketSubmission submitTicket(String userId) {
//        Set<Integer> userNumbers = userNumbersProvider.returnUserNumbers();
//        LotekTicketEntity lotekTicketEntity = new LotekTicketEntity(userId, userNumbers.toString(), LocalDate.now());
//        lotekRepository.save(lotekTicketEntity);
//        return new TicketSubmission(userNumbers, "Los zapisany!");
//    }
//
//    public List<LotekTicketEntity> fetchGameHistoryForAllUsers(){
//        return lotekRepository.findAll();
//    }
//}
