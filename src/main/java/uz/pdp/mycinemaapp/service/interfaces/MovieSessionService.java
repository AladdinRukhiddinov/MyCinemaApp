package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieSessionDto;

import java.util.UUID;

public interface MovieSessionService {

    ApiResponse getAllMovieSessions(int page, int size, String search);
    ApiResponse getMovieSessionById(UUID id);
    ApiResponse addMovieSession(MovieSessionDto dto);
    ApiResponse editMovieSession(UUID id, MovieSessionDto dto);
    ApiResponse deleteMovieSession(UUID id);
}
