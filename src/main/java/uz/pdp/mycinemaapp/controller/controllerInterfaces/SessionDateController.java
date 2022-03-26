package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.SessionDate;
import uz.pdp.mycinemaapp.payload.dtos.SessionDateDto;

import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.BASE_PATH;

@RequestMapping(SessionDateController.SESSION_DATE_CONTROLLER)
public interface SessionDateController {

    String SESSION_DATE_CONTROLLER = BASE_PATH + "/sessionDate";

    @GetMapping
    HttpEntity<?> getAllSessionDates();

    @GetMapping("/{id}")
    HttpEntity<?> getSessionDate(@PathVariable UUID id);

    @PostMapping
    HttpEntity<?> addSessionDate(@RequestBody SessionDateDto dto);

    @PutMapping("/{id}")
    HttpEntity<?> editSessionDate(@PathVariable UUID id, @RequestBody SessionDateDto dto);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteSessionDate(@PathVariable UUID id);

}
