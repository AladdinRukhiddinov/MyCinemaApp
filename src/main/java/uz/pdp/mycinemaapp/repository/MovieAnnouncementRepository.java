package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.MovieAnnouncement;

import java.util.UUID;

public interface MovieAnnouncementRepository extends JpaRepository<MovieAnnouncement, UUID> {
    boolean existsMovieAnnouncementByMovieId(UUID movie_id);
}
