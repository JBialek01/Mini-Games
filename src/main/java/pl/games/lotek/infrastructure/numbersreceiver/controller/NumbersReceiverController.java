package pl.games.lotek.infrastructure.numbersreceiver.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.ticketsreceiver.TicketsReceiverFacade;
import pl.games.lotek.domain.ticketsreceiver.dto.TicketSubmissionDto;

import java.util.HashSet;
import java.util.Set;

@RestController
@Scope("request")
@AllArgsConstructor
public class NumbersReceiverController {

    private final TicketsReceiverFacade ticketsReceiverFacade;

    @PostMapping("/lotekSubmitTicket")
    public ResponseEntity<TicketSubmissionDto> submitTicket(@AuthenticationPrincipal OAuth2User user, @RequestBody @Valid UserNumbersRequestDto requestDto) {
        Set<Integer> userNumbers = new HashSet<>(requestDto.numbers());
        TicketSubmissionDto response = ticketsReceiverFacade.submitTicket(user, userNumbers);
        return ResponseEntity.ok(response);
    }
}
