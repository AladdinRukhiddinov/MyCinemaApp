package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.SeatDto;
import uz.pdp.mycinemaapp.service.SeatServiceImpl;
import uz.pdp.mycinemaapp.util.Constants;

import java.util.UUID;

@RestController
@RequestMapping("/api/seat")
@RequiredArgsConstructor
public class SeatController {

    private final SeatServiceImpl seatService;

    @GetMapping("/getAllSeatsByPage")
    public HttpEntity<?> getAllSeatsByPage(@RequestParam(name = "page") int page, @RequestParam(name = "size",defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        ApiResponse allSeatsByPage = seatService.getAllSeatsByPage(page, size);
        return ResponseEntity.status(allSeatsByPage.isStatus() ? 200 : 409).body(allSeatsByPage);
    }

    @GetMapping
    public HttpEntity<?> getAllSeats() {
        ApiResponse apiResponse = seatService.getAllSeats();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getSeatByRowId/{rowId}")
    public HttpEntity<?> getSeatByRowId(@PathVariable UUID rowId) {
        ApiResponse apiResponse = seatService.getSeatByRowId(rowId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/getSeatByHallId/{halId}")
    public HttpEntity<?> getSeatByHallId(@PathVariable UUID halId) {
        ApiResponse apiResponse = seatService.getSeatByHallId(halId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/getSeatById/{seatId}")
    public HttpEntity<?> getSeatById(@PathVariable UUID seatId) {
        ApiResponse apiResponse = seatService.getSeatById(seatId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addSeat(@RequestBody SeatDto seatDto) {
        ApiResponse apiResponse = seatService.addSeat(seatDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{seatId}")
    public HttpEntity<?> editSeat(@PathVariable UUID seatId, @RequestBody SeatDto seatDto) {
        ApiResponse apiResponse = seatService.editSeat(seatId, seatDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{seatId}")
    public HttpEntity<?> deleteSeat(@PathVariable UUID seatId) {
        ApiResponse apiResponse = seatService.deleteSeat(seatId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

}
