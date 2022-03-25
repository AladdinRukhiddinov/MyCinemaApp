package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.DistributorController;
import uz.pdp.mycinemaapp.entity.Distributor;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.DistributorService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DistributorControllerImpl implements DistributorController {

    private final DistributorService distributorService;


    @Override
    public HttpEntity<?> getAllDistributors() {
        return null;
    }

    @Override
    public HttpEntity<?> getDistributor(UUID id) {
        return null;
    }

    @Override
    public HttpEntity<?> addDistributor(Distributor distributor) {
        ApiResponse apiResponse = distributorService.addDistributor(distributor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> editDistributor(UUID id, Distributor distributor) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteDistributor(UUID id) {
        return null;
    }
}
