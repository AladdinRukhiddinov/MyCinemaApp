package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.mycinemaapp.entity.SessionTime;
import uz.pdp.mycinemaapp.projection.SessionTimeProjection;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {

    boolean existsByTime(LocalTime time);

    @Query(value = "select distinct " +
            "cast(st.id as varchar) as id,\n" +
            "cast(ms.id as varchar) as sessionId,\n" +
            "       time\n" +
            "from session_times st\n" +
            "         join movie_sessions ms on st.id = ms.start_time_id\n" +
            "where ms.hall_id = :hallId\n" +
            "  and movie_announcement_id = :movieAnnouncementId " +
            "and ms.start_date_id = :startDateId", nativeQuery = true)
    List<SessionTimeProjection> getTimesByHallIdAndAnnouncementIdAndStartDateId(UUID hallId, UUID movieAnnouncementId, UUID startDateId);


}
