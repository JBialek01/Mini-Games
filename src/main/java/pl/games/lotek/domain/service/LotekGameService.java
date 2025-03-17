package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.repository.LotekRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.infrastructure.controller.dto.CheckWinDto;
import pl.games.lotek.infrastructure.controller.dto.LotekTicketDto;
import pl.games.lotek.infrastructure.controller.dto.RankingDto;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;
import pl.games.lotek.infrastructure.controller.mapper.LotekTicketMapper;

import java.util.List;

@Component
@Scope("prototype")
@AllArgsConstructor
public class LotekGameService {

    private final LotekRepository lotekRepository;
    private final CheckWinService checkWinService;
    private final AuthenticatedUserService authenticatedUserService;
    private final RankingService rankingService;
    private final SubmitTicketService submitTicketService;


    public TicketSubmissionDto submitTicket(OAuth2User user) {
        return submitTicketService.submitTicket(user);
    }

    public List<CheckWinDto> checkWin(OAuth2User user) {
        return checkWinService.getCheckWinResults(user);
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
