package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.repository.CheckWinRepository;
import pl.games.lotek.domain.repository.LotekRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.infrastructure.controller.dto.LotekTicketDto;
import pl.games.lotek.infrastructure.controller.dto.RankingDto;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.controller.mapper.CheckWinMapper;
import pl.games.lotek.infrastructure.controller.mapper.LotekTicketMapper;

import java.time.LocalDate;
import java.util.List;

@Component
@Scope("prototype")
@AllArgsConstructor
public class LotekGameService {

    private final LotekRepository lotekRepository;
    private final LotekWinningNumbersService lotekWinningNumbersService;
    private final CheckWinService checkWinService;
    private final CheckWinRepository checkWinRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final RankingService rankingService;
    private final SubmitTicketService submitTicketService;


    public TicketSubmissionDto submitTicket(OAuth2User user) {
        return submitTicketService.submitTicket(user);
    }



    public List<CheckWinDto> checkWin(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        lotekWinningNumbersService.getWinningNumbersForYesterday();
        List<LotekTicketEntity> userTickets = lotekRepository.findByUserId(userId);
        if (userTickets.isEmpty()) {
            return List.of();
        }

        for (LotekTicketEntity ticket : userTickets) {
            checkWinService.checkAndSaveResults(userId);
        }

        List<CheckWinEntity> previousDayResults = checkWinRepository.findByUserIdAndDate(userId, LocalDate.now().minusDays(1));
        List<CheckWinDto> checkWinDto = CheckWinMapper.mapToCheckWin(previousDayResults);
        return checkWinDto;
    }

    public ResponseEntity<List<LotekTicketDto>> showAllTickets() {
        List<LotekTicketEntity> lotekTickets = lotekRepository.findAll();
        return ResponseEntity.ok(LotekTicketMapper.mapToLotekTicketDto(lotekTickets));
    }

    public ResponseEntity<List<RankingDto>> getRanking() {
        List<RankingDto> ranking = rankingService.generateRanking();
        return ResponseEntity.ok(ranking);
    }
}
