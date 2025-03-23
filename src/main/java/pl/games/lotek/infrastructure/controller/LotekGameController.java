package pl.games.lotek.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.service.LotekGameService;
import pl.games.lotek.domain.service.LotekUserNumberWebProvider;
import pl.games.lotek.domain.service.UserHitsRankingService;
import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.infrastructure.controller.dto.LotekTicketDto;
import pl.games.lotek.infrastructure.controller.dto.UserHitsRankingDto;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import org.springframework.http.ResponseEntity;
import pl.games.lotek.infrastructure.controller.mapper.CheckWinMapper;
import pl.games.lotek.infrastructure.controller.mapper.LotekTicketMapper;
import pl.games.lotek.infrastructure.controller.mapper.UserHitsRankingMapper;

import java.util.List;
import java.util.Set;

@RestController
@Scope("request")
@AllArgsConstructor
public class LotekGameController {

    private final LotekGameService lotekGameService;
    private final UserHitsRankingService userHitsRankingService;
    private final LotekUserNumberWebProvider userNumbersProvider;

    @GetMapping("/lotekSubmitTicket")
    public ResponseEntity<TicketSubmissionDto> submitTicket(@AuthenticationPrincipal OAuth2User user,
                                                            @RequestParam("number1") Integer number1,
                                                            @RequestParam("number2") Integer number2,
                                                            @RequestParam("number3") Integer number3,
                                                            @RequestParam("number4") Integer number4,
                                                            @RequestParam("number5") Integer number5,
                                                            @RequestParam("number6") Integer number6) {
        userNumbersProvider.addNumbers(Set.of(number1, number2, number3, number4, number5, number6));
        TicketSubmissionDto response = lotekGameService.submitTicket(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkWin")
    public ResponseEntity<List<CheckWinDto>> checkWinResults(@AuthenticationPrincipal OAuth2User user) {
        List<CheckWinEntity> wins = lotekGameService.checkWin(user);
        List<CheckWinDto> winsDtos = CheckWinMapper.mapToCheckWin(wins);
        return ResponseEntity.ok(winsDtos);
    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<List<LotekTicketDto>> fetchGameHistoryForAllUsers(){
        List<LotekTicketEntity> lotekTickets = lotekGameService.getAllTickets();
        List<LotekTicketDto> lotekTicketDtos = LotekTicketMapper.mapToLotekTicketDto(lotekTickets);
        return ResponseEntity.ok(lotekTicketDtos);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserHitsRankingDto>> getRanking() {
        List<UserHitsRankingEntity> ranking = userHitsRankingService.generateRanking();
        List<UserHitsRankingDto> userHitsRankingDtos = UserHitsRankingMapper.mapToRankingDto(ranking);
        return ResponseEntity.ok(userHitsRankingDtos);
    }
}
