package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.RowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/row")
@RequiredArgsConstructor
public class RowController {

    private final RowService rowService;

    @GetMapping("/getRowsByHallId/{hallId}")
    public HttpEntity<?> getRowsByHallId(@PathVariable UUID hallId) {
        ApiResponse apiResponse = rowService.getRowsByHallId(hallId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllRows() {
        ApiResponse apiResponse = rowService.getAllRows();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{rowId}")
    public HttpEntity<?> getRowById(@PathVariable UUID rowId) {
        ApiResponse apiResponse = rowService.getRowById(rowId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addRow(@PathVariable UUID id, @RequestBody Row row) {
        ApiResponse apiResponse = rowService.addRow(id, row);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRow(@PathVariable UUID id, @RequestBody Row row) {
        ApiResponse apiResponse = rowService.editRow(id, row);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRow(@PathVariable UUID id) {
        ApiResponse apiResponse = rowService.deleteRow(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }
}
