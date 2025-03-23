package pl.games.lotek.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import pl.games.auth.AuthenticatedUserService;
import pl.games.lotek.domain.model.CheckWinEntity;
import pl.games.lotek.domain.repository.LotekTicketRepository;
import pl.games.lotek.domain.model.LotekTicketEntity;
import pl.games.lotek.infrastructure.controller.dto.TicketSubmissionDto;

import java.util.List;

@Component
@Scope("prototype")
@AllArgsConstructor
public class LotekGameService {

    private final LotekTicketRepository lotekTicketRepository;
    private final CheckWinService checkWinService;
    private final AuthenticatedUserService authenticatedUserService;
    private final SubmitTicketService submitTicketService;

    public TicketSubmissionDto submitTicket(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return submitTicketService.submitTicket(userId);
    }

    public List<CheckWinEntity> checkWin(OAuth2User user) {
        String userId = authenticatedUserService.getAuthenticatedUserId(user);
        return checkWinService.getCheckWinResults(userId);
    }

    public List<LotekTicketEntity> getAllTickets() {
        return lotekTicketRepository.findAll();
    }
}
