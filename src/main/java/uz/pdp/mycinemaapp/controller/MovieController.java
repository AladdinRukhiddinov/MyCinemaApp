package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieDto;
import uz.pdp.mycinemaapp.service.MovieServiceImpl;
import uz.pdp.mycinemaapp.util.Constants;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;

    @GetMapping
    public HttpEntity<?> getAllMovies(
            @RequestParam(name = "page", defaultValue = Constants.DEFAULT_SIZE) int page,
            @RequestParam(name = "size", defaultValue = "1") int size,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ) {
        ApiResponse apiResponse = movieService.getAllMovies(page, size, search, sort, true);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMovieById(@PathVariable UUID id) {
        ApiResponse apiResponse = movieService.getMovieById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        ApiResponse apiResponse = movieService.addMovie(movieDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editMovie(@PathVariable UUID id, @RequestBody MovieDto movieDto) throws ParseException {
        ApiResponse apiResponse = movieService.editMovie(id, movieDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMovie(@PathVariable UUID id) {
        ApiResponse apiResponse = movieService.deleteMovie(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

}
