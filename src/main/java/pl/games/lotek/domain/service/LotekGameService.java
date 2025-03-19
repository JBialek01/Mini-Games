package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.repository.LotekTicketRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.infrastructure.controller.dto.LotekTicketDto;
import pl.games.lotek.infrastructure.controller.dto.UserHitsRankingDto;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.controller.mapper.LotekTicketMapper;

import java.util.List;

@Component
@Scope("prototype")
@AllArgsConstructor
public class LotekGameService {

    private final LotekTicketRepository lotekTicketRepository;
    private final CheckWinService checkWinService;
    private final AuthenticatedUserService authenticatedUserService;
    private final UserHitsRankingService userHitsRankingService;
    private final SubmitTicketService submitTicketService;


    public TicketSubmissionDto submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return submitTicketService.submitTicket(userId);
    }

    public List<CheckWinDto> checkWin(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return checkWinService.getCheckWinResults(userId);
    }

    public ResponseEntity<List<LotekTicketDto>> showAllTickets() {
        List<LotekTicketEntity> lotekTickets = lotekTicketRepository.findAll();
        return ResponseEntity.ok(LotekTicketMapper.mapToLotekTicketDto(lotekTickets));
    }

    public ResponseEntity<List<UserHitsRankingDto>> getRanking() {
        List<UserHitsRankingDto> ranking = userHitsRankingService.generateRanking();
        return ResponseEntity.ok(ranking);
    }
}
