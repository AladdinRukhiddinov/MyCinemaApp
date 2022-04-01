package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.*;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieSessionDto;
import uz.pdp.mycinemaapp.payload.dtos.ReservedHallDto;
import uz.pdp.mycinemaapp.projection.MovieSessionProjection;
import uz.pdp.mycinemaapp.repository.*;
import uz.pdp.mycinemaapp.service.interfaces.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final MovieAnnouncementRepository movieAnnouncementRepository;
    private final HallRepository hallRepository;
    private final SessionTimeRepository sessionTimeRepository;
    private final SessionDateRepository sessionDateRepository;
    private final MovieRepository movieRepository;

    @Override
    public ApiResponse getAllMovieSessions(int size, int page, String search) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<MovieSessionProjection> all = movieSessionRepository.findAllSessionsByPage(
                pageable,
                search);
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, all);
    }

    @Override
    public ApiResponse getMovieSessionById(UUID id) {
        MovieSessionProjection movieSessionProjection = movieSessionRepository.findBySessionId(id).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_SESSION_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, movieSessionProjection);
    }

    @Override
    public ApiResponse addMovieSession(MovieSessionDto dto) {

        MovieAnnouncement movieAnnouncement = movieAnnouncementRepository.findById(dto.getMovieAnnouncementId()).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_NOT_FOUND"), HttpStatus.NOT_FOUND));

        if (dto.getReservedHallDtoList().isEmpty()) throw new RestException(MessageService.getMessage("LIST_EMPTY"),HttpStatus.NOT_FOUND);

        for (ReservedHallDto reservedHallDto : dto.getReservedHallDtoList()) {
            Hall hall = hallRepository.findById(reservedHallDto.getHallId()).orElseThrow(() -> new RestException(MessageService.getMessage("HALL_NOT_FOUND"), HttpStatus.NOT_FOUND));
            Integer durationInMinutes = movieAnnouncement.getMovie().getDurationInMinutes();
            List<SessionDate> sessionDate = getSessionDate(reservedHallDto);
            List<SessionTime> sessionTime = getSessionTime(reservedHallDto);

                    for (SessionDate date : sessionDate) {
                        for (SessionTime time : sessionTime) {
                            MovieSession movieSession = new MovieSession();
                            movieSession.setMovieAnnouncement(movieAnnouncement);
                            movieSession.setHall(hall);
                            movieSession.setStartDate(date);
                            movieSession.setStartTime(time);
                            SessionTime calculateEndTime = calculateEndTime(durationInMinutes, time);
                            movieSession.setEndTime(calculateEndTime);
                            movieSessionRepository.save(movieSession);
                        }
                    }
            }
            return new ApiResponse(MessageService.getMessage("MOVIE_SESSION_SAVED"), true);
    }


    @Override
    public ApiResponse editMovieSession(UUID id, MovieSessionDto dto) {
        MovieSession editingMovieSession = movieSessionRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_SESSION_NOT_FOUND"), HttpStatus.NOT_FOUND));

        MovieAnnouncement movieAnnouncement = movieAnnouncementRepository.findById(dto.getMovieAnnouncementId()).orElseThrow(() -> new RestException(MessageService.getMessage("MOVIE_ANNOUNCEMENT_NOT_FOUND"), HttpStatus.NOT_FOUND));

        if (dto.getReservedHallDtoList().isEmpty()) throw new RestException(MessageService.getMessage("LIST_EMPTY"),HttpStatus.NOT_FOUND);

        for (ReservedHallDto reservedHallDto : dto.getReservedHallDtoList()) {
            Hall hall = hallRepository.findById(reservedHallDto.getHallId()).orElseThrow(() -> new RestException(MessageService.getMessage("HALL_NOT_FOUND"), HttpStatus.NOT_FOUND));
            Integer durationInMinutes = movieAnnouncement.getMovie().getDurationInMinutes();
            List<SessionDate> sessionDate = getSessionDate(reservedHallDto);
            List<SessionTime> sessionTime = getSessionTime(reservedHallDto);

            for (SessionDate date : sessionDate) {
                for (SessionTime time : sessionTime) {
                    editingMovieSession.setMovieAnnouncement(movieAnnouncement);
                    editingMovieSession.setHall(hall);
                    editingMovieSession.setStartDate(date);
                    editingMovieSession.setStartTime(time);
                    SessionTime calculateEndTime = calculateEndTime(durationInMinutes, time);
                    editingMovieSession.setEndTime(calculateEndTime);
                    movieSessionRepository.save(editingMovieSession);
                }
            }
        }
        return new ApiResponse(MessageService.getMessage("MOVIE_SESSION_EDITED"), true);
    }

    @Override
    public ApiResponse deleteMovieSession(UUID id) {

        try {
            movieSessionRepository.deleteById(id);
            return new ApiResponse(MessageService.getMessage("MOVIE_SESSION_DELETED"), true);
        } catch (RestException e) {
            throw new RestException(MessageService.getMessage("MOVIE_SESSION_NOT_FOUND"), HttpStatus.NOT_FOUND);
        }
    }

    private SessionTime calculateEndTime(Integer durationInMinutes, SessionTime startTime) {

        LocalTime endTime = startTime.getTime().plusMinutes(durationInMinutes + 15);
        if (sessionTimeRepository.existsByTime(endTime)) {
            return sessionTimeRepository.getSessionTimeByTime(endTime);
        } else {
            return sessionTimeRepository.save(new SessionTime(endTime));
        }

    }

    private List<SessionTime> getSessionTime(ReservedHallDto reservedHallDto) {

        List<SessionTime> sessionTimeList = new ArrayList<>();
        for (String startTime : reservedHallDto.getStartTimeList()) {
            LocalTime time = LocalTime.parse(startTime);
            if (sessionTimeRepository.existsByTime(time)) {
                SessionTime sessionTime = sessionTimeRepository.getSessionTimeByTime(time);
                sessionTimeList.add(sessionTime);
            } else {
                SessionTime sessionTime = new SessionTime(time);
                SessionTime save = sessionTimeRepository.save(sessionTime);
                sessionTimeList.add(save);
            }
        }

        return sessionTimeList;
    }

    private List<SessionDate> getSessionDate(ReservedHallDto reservedHallDto) {

        LocalDate startDate = LocalDate.parse(reservedHallDto.getStartDate());
        LocalDate endDate = LocalDate.parse(reservedHallDto.getEndDate());

        List<SessionDate> sessionDateList = new ArrayList<>();
        int in = 0;
        for (int i = startDate.getDayOfYear(); i <= endDate.getDayOfYear(); i++) {
            LocalDate newDate = startDate.plusDays(in++);
            if (sessionDateRepository.existsByDate(newDate)) {
                SessionDate date = sessionDateRepository.getSessionDateByDate(newDate);
                sessionDateList.add(date);
            } else {
                SessionDate date = new SessionDate(newDate);
                SessionDate save = sessionDateRepository.save(date);
                sessionDateList.add(save);
            }
        }
        return sessionDateList;
    }
}
