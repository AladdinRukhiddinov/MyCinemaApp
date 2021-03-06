package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.SessionTimeController;
import uz.pdp.mycinemaapp.payload.dtos.SessionTimeDto;
import uz.pdp.mycinemaapp.service.SessionTimeServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SessionTimeControllerImpl implements SessionTimeController {

    private final SessionTimeServiceImpl sessionTimeService;

    @Override
    public HttpEntity<?> getAllSessionTimes() {
        return ResponseEntity.ok(sessionTimeService.getAllSessionTimes());
    }

    @Override
    public HttpEntity<?> getSessionTime(UUID id) {
        return ResponseEntity.ok(sessionTimeService.getSessionTime(id));
    }

    @Override
    public HttpEntity<?> addSessionTime(SessionTimeDto dto) {
        return ResponseEntity.ok(sessionTimeService.addSessionTime(dto));
    }

    @Override
    public HttpEntity<?> editSessionTime(UUID id, SessionTimeDto dto) {
        return ResponseEntity.ok(sessionTimeService.editSessionTime(id, dto));
    }

    @Override
    public HttpEntity<?> deleteSessionTime(UUID id) {
        return ResponseEntity.ok(sessionTimeService.deleteSessionTime(id));
    }
}
