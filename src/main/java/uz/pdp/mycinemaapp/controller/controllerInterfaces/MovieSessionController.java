package uz.pdp.mycinemaapp.controller.controllerInterfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.dtos.MovieSessionDto;

import java.util.UUID;

import static uz.pdp.mycinemaapp.util.Constants.*;

@RequestMapping(MovieSessionController.MOVIE_SESSION_CONTROLLER)
public interface MovieSessionController {

    String MOVIE_SESSION_CONTROLLER = BASE_PATH + "/movieSession";

    @GetMapping
    HttpEntity<?> getAllMovieSessions(
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE, required = false) int size,
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(name = "search", defaultValue = DEFAULT_SEARCH) String search
    );

    @GetMapping("/getById/{id}")
    HttpEntity<?> getMovieSessionById(@PathVariable UUID id);

    @PostMapping
    HttpEntity<?> addMovieSession(@RequestBody MovieSessionDto dto);

    @PutMapping("/{id}")
    HttpEntity<?> editMovieSession(@PathVariable UUID id, @RequestBody MovieSessionDto dto);

    @DeleteMapping("/{id}")
    HttpEntity<?> deleteMovieSession(@PathVariable UUID id);

}
