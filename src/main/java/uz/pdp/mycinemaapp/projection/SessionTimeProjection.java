package uz.pdp.mycinemaapp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.MovieSession;
import uz.pdp.mycinemaapp.entity.SessionTime;

import java.time.LocalTime;
import java.util.UUID;

@Projection(types = {SessionTime.class, MovieSession.class})
public interface SessionTimeProjection {
    UUID getId();

    LocalTime getTime();

    UUID getMovieSessionId();

}
