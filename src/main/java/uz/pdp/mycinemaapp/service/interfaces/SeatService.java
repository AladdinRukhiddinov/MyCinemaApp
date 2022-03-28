package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.SeatDto;

import java.util.UUID;

public interface SeatService {

    ApiResponse getAllSeatsByPage(int page,int size);
    ApiResponse getAllSeats();
    ApiResponse getSeatByRowId(UUID rowId);
    ApiResponse getSeatByHallId(UUID halId);
    ApiResponse getSeatById(UUID seatId);
    ApiResponse addSeat(SeatDto seatDto);
    ApiResponse editSeat(UUID seatId,SeatDto seatDto);
    ApiResponse deleteSeat(UUID seatId);
    ApiResponse getAvailableSeatsByMovieSessionId(UUID movieSessionId);

}
