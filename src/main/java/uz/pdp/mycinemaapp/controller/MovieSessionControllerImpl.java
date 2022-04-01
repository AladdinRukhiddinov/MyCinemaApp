package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.MovieSessionController;
import uz.pdp.mycinemaapp.payload.dtos.MovieSessionDto;
import uz.pdp.mycinemaapp.service.MovieSessionServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovieSessionControllerImpl implements MovieSessionController {

    private final MovieSessionServiceImpl movieSessionService;

    @Override
    public HttpEntity<?> getAllMovieSessions(int size, int page, String search) {
        return ResponseEntity.ok(movieSessionService.getAllMovieSessions(size, page, search));
    }

    @Override
    public HttpEntity<?> getMovieSessionById(UUID id) {
        return ResponseEntity.ok(movieSessionService.getMovieSessionById(id));
    }

    @Override
    public HttpEntity<?> addMovieSession(MovieSessionDto dto) {
        return ResponseEntity.ok(movieSessionService.addMovieSession(dto));
    }

    @Override
    public HttpEntity<?> editMovieSession(UUID id, MovieSessionDto dto) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteMovieSession(UUID id) {
        return null;
    }
}
