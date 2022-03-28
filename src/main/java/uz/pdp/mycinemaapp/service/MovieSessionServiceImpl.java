package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieSessionDto;
import uz.pdp.mycinemaapp.projection.MovieSessionProjection;
import uz.pdp.mycinemaapp.repository.MovieSessionRepository;
import uz.pdp.mycinemaapp.service.interfaces.MovieSessionService;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;

    @Override
    public ApiResponse getAllMovieSessions(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page-1,size);

        Page<MovieSessionProjection> all = movieSessionRepository.findAllSessionsByPage(
                pageable,
                search);
        return new ApiResponse(MessageService.getMessage("SUCCESS"),true,all);
    }

    @Override
    public ApiResponse getMovieSessionById(UUID id) {
        return null;
    }

    @Override
    public ApiResponse addMovieSession(MovieSessionDto dto) {
        return null;
    }

    @Override
    public ApiResponse editMovieSession(UUID id, MovieSessionDto dto) {
        return null;
    }

    @Override
    public ApiResponse deleteMovieSession(UUID id) {
        return null;
    }
}
