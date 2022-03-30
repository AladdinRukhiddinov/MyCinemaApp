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


        //movie announcement

        if (movieAnnouncementRepository.findById(dto.getMovieAnnouncementId()).isPresent()) {
            for (ReservedHallDto reservedHallDto : dto.getReservedHallDtoList()) {

                if (hallRepository.findById(reservedHallDto.getHallId()).isPresent()) {
                    MovieAnnouncement movieAnnouncement = movieAnnouncementRepository.findById(dto.getMovieAnnouncementId()).get();
                    Integer durationInMinutes = movieAnnouncement.getMovie().getDurationInMinutes();
                    Hall hall = hallRepository.findById(reservedHallDto.getHallId()).get();
                    List<SessionDate> sessionDate = getSessionDate(reservedHallDto);
                    List<SessionTime> sessionTime = getSessionTime(reservedHallDto);

                    for (SessionDate date : sessionDate) {
                        for (SessionTime time : sessionTime) {
                            MovieSession movieSession = new MovieSession();
                            movieSession.setMovieAnnouncement(movieAnnouncement);
                            movieSession.setHall(hall);
                            movieSession.setStartDate(date);
                            movieSession.setStartTime(time);
                            movieSession.setEndTime(calculateEndTime(durationInMinutes, time));
                            movieSessionRepository.save(movieSession);
                        }
                    }

                }

            }
            return new ApiResponse(MessageService.getMessage("SUCCESS"), true);
        }

        return new ApiResponse(MessageService.getMessage("MY_ERROR"), true);

    }

    private SessionTime calculateEndTime(Integer durationInMinutes, SessionTime startTime) {

        LocalTime localTime = startTime.getTime().plusMinutes(durationInMinutes);
        SessionTime sessionTime = null;
        sessionTime = sessionTimeRepository.getSessionTimeByTime(localTime);
        if (sessionTime == null) {
            sessionTime = sessionTimeRepository.save(new SessionTime(localTime));
        }

        return sessionTime;
    }


    public List<SessionTime> getSessionTime(ReservedHallDto reservedHallDto) {
        List<SessionTime> sessionTimeList = new ArrayList<>();
        for (String startTime : reservedHallDto.getStartTimeList()) {
            SessionTime sessionTimeByTime = null;
            sessionTimeByTime = sessionTimeRepository.getSessionTimeByTime(LocalTime.parse(startTime));
            if (sessionTimeByTime == null) {
                sessionTimeByTime = sessionTimeRepository.save(new SessionTime((LocalTime.parse(startTime))));
            }
            sessionTimeList.add(sessionTimeByTime);
        }
        return sessionTimeList;
    }

    public List<SessionDate> getSessionDate(ReservedHallDto reservedHallDto) {
        LocalDate startDate = LocalDate.parse(reservedHallDto.getStartDate());
        LocalDate endDate = LocalDate.parse(reservedHallDto.getEndDate());

        List<SessionDate> sessionDateList = new ArrayList<>();
        int in = 0;
        for (int i = startDate.getDayOfYear(); i <= endDate.getDayOfYear(); i++) {
            SessionDate sessionDate = null;
            sessionDate = sessionDateRepository.getSessionDateByDate(startDate);
            if (sessionDate == null) {
                sessionDate = sessionDateRepository.save(new SessionDate(startDate));
            }
            in++;
            startDate = startDate.plusDays(in);
            sessionDateList.add(sessionDate);
        }
        return sessionDateList;
    }

    @Override
    public ApiResponse editMovieSession(UUID id, MovieSessionDto dto) {
        // TODO: 3/28/2022 Bugunoq tugat!
        return null;
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

    private List<SessionDate> getDates(String startDate, String endDate) {
        SessionDate startedSessionDate = sessionDateRepository.getSessionDateByDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        SessionDate endSessionDate = sessionDateRepository.getSessionDateByDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (startedSessionDate != null && endSessionDate != null) {
            int in = 0;
            for (int i = startedSessionDate.getDate().getDayOfYear(); i < endSessionDate.getDate().getDayOfYear(); i++) {
                LocalDate date = startedSessionDate.getDate().plusDays(in++);
                return null;
            }
            return null;

        }
        return null;
    }
}
