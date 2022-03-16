package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieDto;

import java.util.UUID;

public interface MovieService {

    ApiResponse getAllMovies(int page, int size, String search, String sort, boolean direction);

    ApiResponse getMovieById(UUID id);

    ApiResponse addMovie(MovieDto movieDto);

    ApiResponse editMovie(UUID id, MovieDto movieDto);

    ApiResponse deleteMovie(UUID id);

}
