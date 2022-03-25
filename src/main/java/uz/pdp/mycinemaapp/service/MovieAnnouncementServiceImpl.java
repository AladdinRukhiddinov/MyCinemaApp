package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.MovieAnnouncement;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.mapper.MovieAnnouncementMapper;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieAnnouncementDto;
import uz.pdp.mycinemaapp.repository.MovieAnnouncementRepository;
import uz.pdp.mycinemaapp.repository.MovieRepository;
import uz.pdp.mycinemaapp.service.interfaces.MovieAnnouncementService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieAnnouncementServiceImpl implements MovieAnnouncementService {

    private final MovieAnnouncementRepository movieAnnouncementRepository;
    private final MovieRepository movieRepository;
    private final MovieAnnouncementMapper movieAnnouncementMapper;

    @Override
    public ApiResponse getAllMovieAnnouncements(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieAnnouncement> movieAnnouncements = movieAnnouncementRepository.findAll(pageable);
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, movieAnnouncements);
    }

    @Override
    public ApiResponse getMovieAnnouncement(UUID movieAnnouncementId) {
        MovieAnnouncement movieAnnouncement = movieAnnouncementRepository.findById(movieAnnouncementId).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_NOT_FOUND"),HttpStatus.NOT_FOUND));
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, movieAnnouncement);
    }

    @Override
    public ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto) {
        if (movieRepository.existsMovieById(movieAnnouncementDto.getMovieId())) {
            if (movieAnnouncementRepository.existsMovieAnnouncementByMovieId(movieAnnouncementDto.getMovieId())) {
                throw new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_ALREADY_EXISTS"), HttpStatus.CONFLICT);
            }
            MovieAnnouncement save = movieAnnouncementRepository.save(movieAnnouncementMapper.toMovieAnnouncement(movieAnnouncementDto));
            return new ApiResponse("SUCCESS", true, save);
        }
        throw new RestException(MessageService.getMessage("MOVIE_NOT_FOUND"),HttpStatus.NOT_FOUND);

    }

    @Override
    public ApiResponse editMovieAnnouncement(UUID movieAnnouncementId, MovieAnnouncementDto movieAnnouncementDto) {
        MovieAnnouncement movieAnnouncement = movieAnnouncementRepository.findById(movieAnnouncementId).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_NOT_FOUND"),HttpStatus.NOT_FOUND));
        movieAnnouncement.setIsActive(movieAnnouncementDto.getIsActive() != null ? movieAnnouncementDto.getIsActive() : movieAnnouncement.getIsActive());
        final MovieAnnouncement save = movieAnnouncementRepository.save(movieAnnouncement);
        return new ApiResponse(MessageService.getMessage("MOVIE_ANNOUNCEMENT_EDITED"), true, save);
    }

    @Override
    public ApiResponse deleteMovieAnnouncement(UUID movieAnnouncementId) {
        if (movieAnnouncementRepository.existsById(movieAnnouncementId)) {
            movieAnnouncementRepository.deleteById(movieAnnouncementId);
            return ApiResponse.successResponse(MessageService.getMessage("MOVIE_ANNOUNCEMENT_DELETED"));
        }
        throw new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_NOT_FOUND"),HttpStatus.NOT_FOUND);
    }
}
