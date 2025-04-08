package pl.games.lotek.domain.ticketsreceiver;

import pl.games.auth.AuthenticatedUserService;

class TicketsReceiverFacadeConfiguration {

    public static TicketsReceiverFacade ticketsReceiverFacade(final LotekTicketRepository ticketRepository) {
        NumbersValidator numbersValidator = new NumbersValidator();
        TicketSubmitter ticketSubmitter = new TicketSubmitter(ticketRepository, numbersValidator);
        AuthenticatedUserService authenticatedUserService = new AuthenticatedUserService();
        TicketsRetriever ticketsRetriever = new TicketsRetriever(ticketRepository);
        return new TicketsReceiverFacade(ticketSubmitter, authenticatedUserService, ticketsRetriever);
    }

}
