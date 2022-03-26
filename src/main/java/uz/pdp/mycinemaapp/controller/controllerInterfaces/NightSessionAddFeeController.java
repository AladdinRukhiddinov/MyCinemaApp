package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.dtos.NightSessionAddFeeDto;

import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.BASE_PATH;

@RequestMapping(NightSessionAddFeeController.NIGHT_SESSION_ADD_FEE_CONTROLLER)
public interface NightSessionAddFeeController {

    String NIGHT_SESSION_ADD_FEE_CONTROLLER = BASE_PATH + "/nightSessionAddFee";

    @GetMapping
    HttpEntity<?> getAllNightSessionAddFees();

    @GetMapping("/{id}")
    HttpEntity<?> getNightSessionAddFee(@PathVariable UUID id);

    @PostMapping
    HttpEntity<?> addNightSessionAddFee(@RequestBody NightSessionAddFeeDto dto);

    @PutMapping("/{id}")
    HttpEntity<?> editNightSessionAddFee(@PathVariable UUID id, @RequestBody NightSessionAddFeeDto dto);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteNightSessionAddFee(@PathVariable UUID id);

}
