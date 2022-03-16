package uz.pdp.mycinemaapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.mycinemaapp.entity.Movie;
import uz.pdp.mycinemaapp.projection.CustomMovie;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @Query(value = "select\n" +
            "       cast(m.id as varchar) as id,\n" +
            "       title,\n" +
            "       cast(release_date as date)as releaseDate,\n" +
            "       cast(a.id as varchar) as coverImgId\n" +
            "from movie m\n" +
            "join attachments a on a.id = m.cover_img_id\n" +
            "where lower(title) like lower(concat('%', :search, '%'))", nativeQuery = true)
    Page<CustomMovie> findAllByPage(Pageable pageable, @Param("search") String search);

}
