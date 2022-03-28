package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class TicketDto {
    private UUID movieSessionId;

    private UUID seatId;

}
