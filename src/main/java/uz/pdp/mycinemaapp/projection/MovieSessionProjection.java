package uz.pdp.mycinemaapp.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.Movie;
import uz.pdp.mycinemaapp.entity.MovieAnnouncement;
import uz.pdp.mycinemaapp.entity.MovieSession;
import uz.pdp.mycinemaapp.entity.SessionDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Projection(types = {MovieAnnouncement.class, MovieSession.class, Movie.class, SessionDate.class})
public interface MovieSessionProjection {

    UUID getMovieAnnouncementId();

    UUID getMovieId();

    String getTitle();

    UUID getCoverImg();

    UUID getStartDateId();

    LocalDate getStartDate();

    @Value("#{@hallRepository.getHallsAndTimesByMovieAnnouncementIdAndStartDateId(target.movieAnnouncementId, target.startDateId)}")
    List<HallAndTimesProjectionForSession> getHalls();

}
