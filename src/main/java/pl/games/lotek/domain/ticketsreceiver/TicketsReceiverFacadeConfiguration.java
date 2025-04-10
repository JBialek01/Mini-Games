package pl.games.lotek.domain.ticketsreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.games.auth.AuthenticatedUserService;

@Configuration
class TicketsReceiverFacadeConfiguration {

    @Bean
    TicketsReceiverFacade ticketsReceiverFacade(LotekTicketRepository ticketRepository) {
        NumbersValidator numbersValidator = new NumbersValidator();
        TicketSubmitter ticketSubmitter = new TicketSubmitter(ticketRepository, numbersValidator);
        AuthenticatedUserService authenticatedUserService = new AuthenticatedUserService();
        TicketsRetriever ticketsRetriever = new TicketsRetriever(ticketRepository);
        return new TicketsReceiverFacade(ticketSubmitter, authenticatedUserService, ticketsRetriever);
    }

}
