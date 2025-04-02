package pl.games.lotek.infrastructure.numbersreceiver.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.numbersreceiver.NumbersReceiverFacade;
import pl.games.lotek.domain.numbersreceiver.dto.TicketSubmissionDto;
import pl.games.lotek.domain.util.LotekUserNumbersWebProvider;

import java.util.Set;

@RestController
@Scope("request")
@AllArgsConstructor
public class NumbersReceiverController {

    private final NumbersReceiverFacade numbersReceiverFacade;
    private final LotekUserNumbersWebProvider userNumbersProvider;

    @GetMapping("/lotekSubmitTicket")
    public ResponseEntity<TicketSubmissionDto> submitTicket(@AuthenticationPrincipal OAuth2User user,
                                                            @RequestParam("number1") Integer number1,
                                                            @RequestParam("number2") Integer number2,
                                                            @RequestParam("number3") Integer number3,
                                                            @RequestParam("number4") Integer number4,
                                                            @RequestParam("number5") Integer number5,
                                                            @RequestParam("number6") Integer number6) {
        userNumbersProvider.addNumbers(Set.of(number1, number2, number3, number4, number5, number6));
        TicketSubmissionDto response = numbersReceiverFacade.submitTicket(user);
        return ResponseEntity.ok(response);
    }
}
