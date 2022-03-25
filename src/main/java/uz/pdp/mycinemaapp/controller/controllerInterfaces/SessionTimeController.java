package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.entity.SessionTime;

import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.BASE_PATH;

@RequestMapping(SessionTimeController.SESSION_TIME_CONTROLLER)
public interface SessionTimeController {

    String SESSION_TIME_CONTROLLER = BASE_PATH + "/sessionTime";

    @GetMapping
    HttpEntity<?> getAllSessionTimes();

    @GetMapping("/{id}")
    HttpEntity<?> getSessionTime(@PathVariable UUID id);

    @PostMapping
    HttpEntity<?> addSessionTime(@RequestBody SessionTime date);

    @PutMapping("/{id}")
    HttpEntity<?> editSessionTime(@PathVariable UUID id, @RequestBody SessionTime date);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteSessionTime(@PathVariable UUID id);

}
