package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.entity.Seat;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.SeatDto;
import uz.pdp.mycinemaapp.repository.PriceCategoryRepository;
import uz.pdp.mycinemaapp.repository.RowRepository;
import uz.pdp.mycinemaapp.repository.SeatRepository;
import uz.pdp.mycinemaapp.service.interfaces.SeatService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final RowRepository rowRepository;
    private final PriceCategoryRepository priceCategoryRepository;

    @Override
    public ApiResponse getAllSeatsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Seat> seats = seatRepository.findAll(pageable);
        if (seats.getSize()==0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, seats);
    }

    @Override
    public ApiResponse getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        if (seats.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, seats);
    }

    @Override
    public ApiResponse getSeatByRowId(UUID rowId) {
        List<Seat> byRowIdList = seatRepository.findByRowId(rowId);
        if (byRowIdList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, byRowIdList);
    }

    @Override
    public ApiResponse getSeatByHallId(UUID halId) {
        List<Seat> byHallIdList = seatRepository.findByHallId(halId);
        if (byHallIdList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, byHallIdList);
    }

    @Override
    public ApiResponse getSeatById(UUID seatId) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        if (optionalSeat.isEmpty()) {
            return new ApiResponse("Seat not found!", false);
        }
        return new ApiResponse("Success", true, optionalSeat.get());
    }

    @Override
    public ApiResponse addSeat(SeatDto seatDto) {
        try {
            Seat seat = new Seat();
            seat.setNumber(seatDto.getNumber());
            seat.setRow(rowRepository.getById(seatDto.getRowId()));
            seat.setPriceCategory(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
            Seat savedSeat = seatRepository.save(seat);
            return new ApiResponse("Successfully added!", true, savedSeat);
        } catch (Exception e){
            return new ApiResponse("Error! Maybe seat already exists!", false);
        }

    }

    @Override
    public ApiResponse editSeat(UUID seatId, SeatDto seatDto) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        if (optionalSeat.isEmpty()) {
            return new ApiResponse("Seat not found!", false);
        }
        try {
            Seat editingSeat = optionalSeat.get();
            editingSeat.setNumber(seatDto.getNumber());
            editingSeat.setRow(rowRepository.getById(seatDto.getRowId()));
            editingSeat.setPriceCategory(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
            Seat savedSeat = seatRepository.save(editingSeat);
            return new ApiResponse("Successfully edited!", true, savedSeat);
        }catch (Exception e){
            return new ApiResponse("Error! Maybe seat already exists!", false);
        }

    }

    @Override
    public ApiResponse deleteSeat(UUID seatId) {
        try {
            seatRepository.deleteById(seatId);
            return new ApiResponse("Successfully deleted!", true);
        }catch (Exception e){
            return new ApiResponse("Seat not found!", false);
        }

    }
}
