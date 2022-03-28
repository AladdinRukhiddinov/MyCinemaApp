package uz.pdp.mycinemaapp.service.interfaces;


import uz.pdp.mycinemaapp.entity.MovieSession;
import uz.pdp.mycinemaapp.entity.Seat;
import uz.pdp.mycinemaapp.entity.Ticket;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.TicketDto;

import java.util.UUID;

public interface TicketService {

    ApiResponse createAndAddTicketToUsersCart(TicketDto ticketDto);

    void scheduleDeleteTicket(Ticket ticket);

    ApiResponse purchaseTicket(UUID ticketId);

    ApiResponse refundTicket(TicketDto ticketDto);

    ApiResponse getTicketsByUserId(UUID userId);

    Double getTicketFinalPrice(MovieSession movieSession, Seat seat);

}
