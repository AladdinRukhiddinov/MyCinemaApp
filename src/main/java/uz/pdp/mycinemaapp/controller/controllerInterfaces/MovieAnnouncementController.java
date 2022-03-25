package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.dtos.MovieAnnouncementDto;

import javax.validation.Valid;
import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.*;

@RequestMapping(MovieAnnouncementController.MOVIE_ANNOUNCEMENT_CONTROLLER)
public interface MovieAnnouncementController {

    String MOVIE_ANNOUNCEMENT_CONTROLLER = BASE_PATH + "/movieAnnouncement";

    @GetMapping("/{id}")
    HttpEntity<?> getMovieAnnouncement(@PathVariable(name = "id") UUID id);

    @GetMapping()
    HttpEntity<?> getAllMovieAnnouncements(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                        @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size);

    @PostMapping
    HttpEntity<?> addMovieAnnouncement(@RequestBody MovieAnnouncementDto dto);

    @PutMapping(value = "/{id}")
    HttpEntity<?> editMovieAnnouncement(@PathVariable UUID id,
                                        @RequestBody MovieAnnouncementDto dto);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteMovieAnnouncement(@PathVariable UUID id);


}
