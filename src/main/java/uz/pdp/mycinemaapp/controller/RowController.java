package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.RowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/row")
@RequiredArgsConstructor
public class RowController {

    private final RowService rowService;

    @GetMapping("/getRowByHallId/{hallId}")
    public HttpEntity<?> getRowsByHallId(@PathVariable UUID hallId){
        ApiResponse apiResponse = rowService.getRowsByHallId(hallId);
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllRows(){
        ApiResponse apiResponse = rowService.getAllRows();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }
}
