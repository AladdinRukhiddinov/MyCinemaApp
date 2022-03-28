package uz.pdp.mycinemaapp.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.Hall;

import java.util.List;
import java.util.UUID;

@Projection(types = Hall.class)
public interface HallAndTimesProjectionForSession {
    UUID getId();

    String getName();

//
//    UUID getMovieAnnouncementId();
//
//    UUID getStartDateId();

    @Value("#{@sessionTimeRepository.getTimesByHallIdAndAnnouncementIdAndStartDateId(target.id, target.movieAnnouncementId, target.startDateId)}")
    List<SessionTimeProjection> getTimes();

}
