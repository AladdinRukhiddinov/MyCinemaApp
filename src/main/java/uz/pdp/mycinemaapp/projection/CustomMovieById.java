package uz.pdp.mycinemaapp.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.Movie;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Projection(types = {Movie.class})
public interface CustomMovieById {

    UUID getId();

    String getTitle();

    UUID getCoverImg();

    LocalDate getReleaseDate();


    // todo get more fields

    @Value("#{@genreRepository.getGenresByMovieId(target.id)}")
    List<GenreProjection> getGenres();


}
