package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.Distributor;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.DistributorService;

@RestController
@RequestMapping("/api/distributor")
@RequiredArgsConstructor
public class DistributorController {

    private final DistributorService distributorService;

    @GetMapping
    public HttpEntity<?> getAllDistributors(){
        ApiResponse apiResponse = distributorService.getAllDistributors();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDistributor(@PathVariable Integer id){
        ApiResponse apiResponse = distributorService.getDistributor(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addDistributor(@RequestBody Distributor distributor){
        ApiResponse apiResponse = distributorService.addDistributor(distributor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDistributor(@PathVariable Integer id,@RequestBody Distributor distributor){
        ApiResponse apiResponse = distributorService.editDistributor(id,distributor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDistributor(@PathVariable Integer id){
        ApiResponse apiResponse = distributorService.deleteDistributor(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }


}
