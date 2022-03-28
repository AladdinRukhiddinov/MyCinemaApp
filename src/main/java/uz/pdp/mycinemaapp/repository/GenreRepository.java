package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.mycinemaapp.entity.Genre;
import uz.pdp.mycinemaapp.projection.GenreProjection;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    @Query(value = "select cast(g.id as varchar), g.name as name from genres g" +
            " join movie_genres mg on g.id = mg.genres_id " +
            "join movie m on mg.movies_id = m.id " +
            "where m.id = :movieId", nativeQuery = true)
    List<GenreProjection> getGenresByMovieId(UUID movieId);

}
