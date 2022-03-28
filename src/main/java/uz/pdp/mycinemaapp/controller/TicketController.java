package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.dtos.TicketDto;
import uz.pdp.mycinemaapp.service.TicketServiceImpl;
import uz.pdp.mycinemaapp.service.interfaces.TicketService;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketService;

    @GetMapping("/get-my-tickets")
    public HttpEntity<?> getTicketsByUserId() {
        // TODO: 3/28/2022 GET CURRENT USER'S ID
        UUID userId = UUID.fromString("ae4573d0-099c-420c-ab0a-3aaddc6a4c26");
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }


    @PostMapping("/add-to-cart")
    public HttpEntity<?> createAndAddTicketToUsersCart(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.createAndAddTicketToUsersCart(ticketDto));
    }


    @GetMapping("/purchase/{ticketId}")
    public HttpEntity<?> purchaseTicket(@PathVariable UUID ticketId) {
        return ResponseEntity.ok(ticketService.purchaseTicket(ticketId));
    }


}
