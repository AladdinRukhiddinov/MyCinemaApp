package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.PriceCategory;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.PriceCategoryService;

import java.util.UUID;

@RestController
@RequestMapping("/api/priceCategory")
@RequiredArgsConstructor
public class PriceCategoryController {

    private final PriceCategoryService priceCategoryService;

    @GetMapping
    public HttpEntity<?> getAllPriceCategories(){
        ApiResponse apiResponse = priceCategoryService.getAllPriceCategories();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPriceCategory(@PathVariable UUID id){
        ApiResponse apiResponse = priceCategoryService.getPriceCategoryById(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addPriceCategory(@RequestBody PriceCategory priceCategory){
        ApiResponse apiResponse = priceCategoryService.addPriceCategory(priceCategory);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPriceCategory(@PathVariable UUID id,@RequestBody PriceCategory priceCategory){
        ApiResponse apiResponse = priceCategoryService.editPriceCategory(id,priceCategory);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePriceCategory(@PathVariable UUID id){
        ApiResponse apiResponse = priceCategoryService.deletePriceCategory(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }
}