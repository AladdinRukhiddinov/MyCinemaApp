package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.Distributor;

import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.BASE_PATH;

@RequestMapping(DistributorController.DISTRIBUTOR_CONTROLLER)
public interface DistributorController {

    String DISTRIBUTOR_CONTROLLER = BASE_PATH + "/distributor";

    @GetMapping
    HttpEntity<?> getAllDistributors();

    @GetMapping("/{id}")
    HttpEntity<?> getDistributor(@PathVariable UUID id);

    @PostMapping
    HttpEntity<?> addDistributor(@RequestBody Distributor distributor);

    @PutMapping("/{id}")
    HttpEntity<?> editDistributor(@PathVariable UUID id, @RequestBody Distributor distributor);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteDistributor(@PathVariable UUID id);

}
