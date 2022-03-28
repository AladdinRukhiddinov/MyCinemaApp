package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.projection.HallAndTimesProjectionForSession;

import java.util.List;
import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {
    @Query(nativeQuery = true, value = "select distinct" +
            " cast(h.id as varchar) as id,\n" +
            "       h.name, \n" +
            " cast(rh.start_date_id as varchar) as startDateId,\n" +
            " cast(movie_announcement_id as varchar) as movieAnnouncementId\n" +
            "from halls h\n" +
            "         join movie_sessions rh on h.id = rh.hall_id\n" +
            "where rh.start_date_id = :startDateId\n" +
            "  and movie_announcement_id = :movieAnnouncementId")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(UUID movieAnnouncementId, UUID startDateId);

}
