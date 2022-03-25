package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.SessionDateController;
import uz.pdp.mycinemaapp.entity.SessionDate;
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
    public HttpEntity<?> addSessionDate(SessionDate date) {
        return ResponseEntity.ok(sessionDateService.addSessionDate(date));
    }

    @Override
    public HttpEntity<?> editSessionDate(UUID id, SessionDate date) {
        return ResponseEntity.ok(sessionDateService.editSessionDate(id,date));
    }

    @Override
    public HttpEntity<?> deleteSessionDate(UUID id) {
        return ResponseEntity.ok(sessionDateService.deleteSessionDate(id));
    }
}
