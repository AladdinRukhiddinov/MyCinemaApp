package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.NightSessionAddFeeController;
import uz.pdp.mycinemaapp.payload.dtos.NightSessionAddFeeDto;
import uz.pdp.mycinemaapp.service.NightSessionAddFeeServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NightSessionAddFeeControllerImpl implements NightSessionAddFeeController {

    private final NightSessionAddFeeServiceImpl nightSessionAddFeeService;

    @Override
    public HttpEntity<?> getAllNightSessionAddFees() {
        return ResponseEntity.ok(nightSessionAddFeeService.getAllNightSessionAddFees());
    }

    @Override
    public HttpEntity<?> getNightSessionAddFee(UUID id) {
        return ResponseEntity.ok(nightSessionAddFeeService.getNightSessionAddFee(id));
    }

    @Override
    public HttpEntity<?> addNightSessionAddFee(NightSessionAddFeeDto dto) {
        return ResponseEntity.ok(nightSessionAddFeeService.addNightSessionAddFee(dto));
    }

    @Override
    public HttpEntity<?> editNightSessionAddFee(UUID id, NightSessionAddFeeDto dto) {
        return ResponseEntity.ok(nightSessionAddFeeService.editNightSessionAddFee(id,dto));
    }

    @Override
    public HttpEntity<?> deleteNightSessionAddFee(UUID id) {
        return ResponseEntity.ok(nightSessionAddFeeService.deleteNightSessionAddFee(id));
    }
}
