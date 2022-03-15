package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.HallService;

import java.util.UUID;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping
    public HttpEntity<?> getAllHalls(){
        ApiResponse apiResponse = hallService.getAllHalls();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getHall(@PathVariable UUID id){
        ApiResponse apiResponse = hallService.getHallById(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addHall(@RequestBody Hall hall){
        ApiResponse apiResponse = hallService.addHall(hall);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editHall(@PathVariable UUID id,@RequestBody Hall hall){
        ApiResponse apiResponse = hallService.editHall(id,hall);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHall(@PathVariable UUID id){
        ApiResponse apiResponse = hallService.deleteHall(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }
}
