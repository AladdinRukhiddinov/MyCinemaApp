package uz.pdp.mycinemaapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.mycinemaapp.entity.MovieSession;
import uz.pdp.mycinemaapp.projection.MovieSessionProjection;

import java.util.Optional;
import java.util.UUID;

public interface MovieSessionRepository extends JpaRepository<MovieSession, UUID> {

    @Query(nativeQuery = true, value = "select distinct" +
//            "       cast(ms.id as varchar)          as id,\n" +
            "       cast(ma.id as varchar)    as movieAnnouncementId,\n" +
            "       cast(ma.movie_id as varchar)    as movieId,\n" +
            "       m.title                         as title,\n" +
            "       cast(m.cover_img_id as varchar) as coverImg,\n" +
            "       cast(sd.id as varchar)          as startDateId, \n" +
            "       sd.date                         as startDate\n" +
            "from movie_sessions ms\n" +
            "         join movie_announcement ma on ms.movie_announcement_id = ma.id " +
            "         join movie m on m.id = ma.movie_id\n" +
            "         join session_dates sd on ms.start_date_id = sd.id\n" +
            "where ma.is_active and  lower(m.title) like lower(concat('%',:search,'%')) and sd.date >= cast(now() as date)\n" +
            "order by sd.date")
    Page<MovieSessionProjection> findAllSessionsByPage(Pageable pageable, String search);

    @Query(nativeQuery = true,
            value = "select cast(ma.id as varchar)    as movieAnnouncementId,\n" +
                    "   cast(ma.movie_id as varchar)    as movieId,\n" +
                    "   m.title                         as movieTitle,\n" +
                    "   cast(m.cover_img_id as varchar) as movieCoverImgId,\n" +
                    "   cast(sd.id as varchar)          as startDateId, \n" +
                    "   sd.date                         as startDate\n" +
                    "from movie_sessions ms\n"+
                    "         join movie_announcement ma on ms.movie_announcement_id = ma.id\n" +
                    "         join movie m on m.id = ma.movie_id\n" +
                    "         join session_dates sd on ms.start_date_id = sd.id\n" +
                    "where ma.is_active=true and ms.id=:id\n")
    Optional <MovieSessionProjection> findBySessionId(UUID id);

}
