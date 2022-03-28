package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.*;
import uz.pdp.mycinemaapp.entity.enums.TicketStatus;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.TicketDto;
import uz.pdp.mycinemaapp.projection.TicketProjection;
import uz.pdp.mycinemaapp.repository.*;
import uz.pdp.mycinemaapp.service.interfaces.TicketService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public ApiResponse createAndAddTicketToUsersCart(TicketDto ticketDto) {
        MovieSession movieSession = movieSessionRepository.findById(ticketDto.getMovieSessionId()).orElseThrow(() ->
                new RestException(MessageService.getMessage("MOVIE_SESSION_NOT_FOUND"), HttpStatus.NOT_FOUND));

        Seat seat = seatRepository.findById(ticketDto.getSeatId()).orElseThrow(() ->
                new RestException(MessageService.getMessage("SEAT_NOT_FOUND"),HttpStatus.NOT_FOUND));

        // TODO: 3/28/2022 FINISH IMPLEMENTING THIS METHOD
        Double finalPrice = getTicketFinalPrice(movieSession, seat);

        // TODO: 3/28/2022 WILL CHANGE IT LATER TO CURRENT USER
        User user = userRepository.findByUserName("test").orElseThrow(() ->
                new RestException(MessageService.getMessage("USER_NOT_FOUND"),HttpStatus.NOT_FOUND));

        Ticket ticket = new Ticket(movieSession, seat, finalPrice, user);

        Ticket savedTicket = ticketRepository.save(ticket);

        scheduleDeleteTicket(savedTicket);

        return new ApiResponse("success", true, null);

    }


    @Override
    public ApiResponse getTicketsByUserId(UUID userId) {
        List<TicketProjection> ticketsByUserId = ticketRepository.getTicketByUserId(userId);
        return new ApiResponse("success", true, ticketsByUserId);
    }

    @Override
    public Double getTicketFinalPrice(MovieSession movieSession, Seat seat) {
        // TODO: 3/28/2022 CALCULATE TICKET FINAL PRICE
        return 50000.0;
    }

    @Override
    public void scheduleDeleteTicket(Ticket ticket) {
        try {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Ticket ticketFromDb = ticketRepository.findById(ticket.getId()).orElseThrow(() ->
                            new RestException(MessageService.getMessage("TICKET_NOT_FOUND"),HttpStatus.NOT_FOUND));
                    if (ticketFromDb.getStatus().equals(TicketStatus.NEW)) {
                        System.out.println(ticketFromDb.getId() + " Ticket deleted... " + new Date());
                        ticketRepository.delete(ticketFromDb);
                    }
                }
            };

            Timer timer = new Timer();
//            long delayTime =  THIRTY_MINUTES_IN_MILLISECOND;
            long delayTime = 15000;
            System.out.println("Scheduler started..." + new Date());
            System.out.println("Delay: " + delayTime);
            timer.schedule(timerTask, delayTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ApiResponse purchaseTicket(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new RestException(MessageService.getMessage("TICKET_NOT_FOUND"),HttpStatus.NOT_FOUND));

        purchaseHistoryRepository.save(new PurchaseHistory(ticket.getUser(), ticket));

        ticket.setStatus(TicketStatus.PURCHASED);
        ticket.setUser(null);
        ticketRepository.save(ticket);


        return new ApiResponse(MessageService.getMessage("TICKET_SAVED"), true);

    }

    @Override
    public ApiResponse refundTicket(TicketDto ticketDto) {
        return null;
    }


}
