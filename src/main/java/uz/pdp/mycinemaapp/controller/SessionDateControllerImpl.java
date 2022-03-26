package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.SessionDateController;
import uz.pdp.mycinemaapp.payload.dtos.SessionDateDto;
import uz.pdp.mycinemaapp.service.SessionDateServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SessionDateControllerImpl implements SessionDateController {

    private final SessionDateServiceImpl sessionDateService;

    @Override
    public HttpEntity<?> getAllSessionDates() {
        return ResponseEntity.ok(sessionDateService.getAllSessionDates());
    }

    @Override
    public HttpEntity<?> getSessionDate(UUID id) {
        return ResponseEntity.ok(sessionDateService.getSessionDate(id));
    }

    @Override
    public HttpEntity<?> addSessionDate(SessionDateDto dto) {
        return ResponseEntity.ok(sessionDateService.addSessionDate(dto));
    }

    @Override
    public HttpEntity<?> editSessionDate(UUID id, SessionDateDto dto) {
        return ResponseEntity.ok(sessionDateService.editSessionDate(id, dto));
    }

    @Override
    public HttpEntity<?> deleteSessionDate(UUID id) {
        return ResponseEntity.ok(sessionDateService.deleteSessionDate(id));
    }
}
