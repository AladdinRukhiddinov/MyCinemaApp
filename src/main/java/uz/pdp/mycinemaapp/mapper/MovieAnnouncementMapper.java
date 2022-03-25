package uz.pdp.mycinemaapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.mycinemaapp.entity.Movie;
import uz.pdp.mycinemaapp.entity.MovieAnnouncement;
import uz.pdp.mycinemaapp.payload.dtos.MovieAnnouncementDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

//@Mapper(componentModel = "spring", uses = {MovieAnnouncement.class, Movie.class})
@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface MovieAnnouncementMapper {

    @Mapping(target = "movie.id", source = "dto.movieId")
    MovieAnnouncement toMovieAnnouncement(MovieAnnouncementDto dto);

    MovieAnnouncementDto toMovieAnnouncementDto(MovieAnnouncement movieAnnouncement);

}
