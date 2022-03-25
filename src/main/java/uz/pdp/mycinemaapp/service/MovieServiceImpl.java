package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.mycinemaapp.entity.*;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieDto;
import uz.pdp.mycinemaapp.projection.CustomMovie;
import uz.pdp.mycinemaapp.repository.*;
import uz.pdp.mycinemaapp.service.interfaces.MovieService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final AttachmentRepository attachmentRepository;
    private final DirectorRepository directorRepository;
    private final DistributorRepository distributorRepository;
    private final GenreRepository genreRepository;

    @Override
    public ApiResponse getAllMovies(int page, int size, String search, String sort, boolean direction) {
        Pageable pageable = PageRequest.of(page - 1, size, direction ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        try {
            Page<CustomMovie> all = movieRepository.findAllByPage(pageable, search);
            return new ApiResponse("success", true, all);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    @Override
    public ApiResponse getMovieById(UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            return new ApiResponse("Movie not found!", false);
        }
        return new ApiResponse("success", true, optionalMovie.get());
    }

    @Override
    public ApiResponse addMovie(MovieDto movieDto) {
        List<Actor> actors = new ArrayList<>();
        movieDto.getActors().forEach(uuid -> actorRepository.findById(uuid).ifPresent(actors::add));
        if (actors.isEmpty()) return new ApiResponse("Actors is empty!", false);

        List<Director> directors = new ArrayList<>();
        movieDto.getDirectors().forEach(uuid -> directorRepository.findById(uuid).ifPresent(directors::add));
        if (directors.isEmpty()) return new ApiResponse("Directors is empty!", false);

        List<Genre> genres = new ArrayList<>();
        movieDto.getGenres().forEach(uuid -> genreRepository.findById(uuid).ifPresent(genres::add));
        if (genres.isEmpty()) return new ApiResponse("Genres is empty!", false);

        Optional<Attachment> optionalAttachmentImg = attachmentRepository.findById(movieDto.getCoverImgId());
        if (optionalAttachmentImg.isEmpty()) return new ApiResponse("Cover image is empty!", false);

        Optional<Attachment> optionalAttachmentVideo = attachmentRepository.findById(movieDto.getTrailerVideoId());
        if (optionalAttachmentVideo.isEmpty()) return new ApiResponse("Trailer video is empty!", false);

        Optional<Distributor> optionalDistributor = distributorRepository.findById(movieDto.getDistributorId());
        if (optionalDistributor.isEmpty()) return new ApiResponse("Distributor is empty!", false);
        try {
            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setDurationInMinutes(movieDto.getDurationInMin());
            movie.setMin_price(movieDto.getMinPrice());
            movie.setCoverImg(optionalAttachmentImg.get());
            movie.setTrailerVideo(optionalAttachmentVideo.get());
            movie.setReleaseDate(LocalDate.parse(movieDto.getReleaseDate()));
            movie.setBudget(movieDto.getBudget());
            movie.setDistributor(optionalDistributor.get());
            movie.setDistributor_share_in_percent(movieDto.getDistributorShareInPercentage());
            movie.setActors(actors);
            movie.setDirectors(directors);
            movie.setGenres(genres);
            Movie saveMovie = movieRepository.save(movie);
            return new ApiResponse("Successfully added!", true, saveMovie);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }

    @Override
    public ApiResponse editMovie(UUID id, MovieDto movieDto) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) return new ApiResponse("Movie not found!", false);

        List<Actor> actors = new ArrayList<>();
        movieDto.getActors().forEach(uuid -> actorRepository.findById(uuid).ifPresent(actors::add));
        if (actors.isEmpty()) return new ApiResponse("Actors is empty!", false);

        List<Director> directors = new ArrayList<>();
        movieDto.getDirectors().forEach(uuid -> directorRepository.findById(uuid).ifPresent(directors::add));
        if (directors.isEmpty()) return new ApiResponse("Directors is empty!", false);

        List<Genre> genres = new ArrayList<>();
        movieDto.getGenres().forEach(uuid -> genreRepository.findById(uuid).ifPresent(genres::add));
        if (genres.isEmpty()) return new ApiResponse("Genres is empty!", false);

        Optional<Attachment> optionalAttachmentImg = attachmentRepository.findById(movieDto.getCoverImgId());
        if (optionalAttachmentImg.isEmpty()) return new ApiResponse("Cover image is empty!", false);

        Optional<Attachment> optionalAttachmentVideo = attachmentRepository.findById(movieDto.getTrailerVideoId());
        if (optionalAttachmentVideo.isEmpty()) return new ApiResponse("Trailer video is empty!", false);

        Optional<Distributor> optionalDistributor = distributorRepository.findById(movieDto.getDistributorId());
        if (optionalDistributor.isEmpty()) return new ApiResponse("Distributor is empty!", false);

        try {
            Movie movie = optionalMovie.get();
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setDurationInMinutes(movie.getDurationInMinutes());
            movie.setMin_price(movieDto.getMinPrice());
            movie.setCoverImg(optionalAttachmentImg.get());
            movie.setTrailerVideo(optionalAttachmentVideo.get());
            movie.setReleaseDate(LocalDate.parse(movieDto.getReleaseDate()));
            movie.setBudget(movieDto.getBudget());
            movie.setDistributor(optionalDistributor.get());
            movie.setDistributor_share_in_percent(movieDto.getDistributorShareInPercentage());
            movie.setActors(actors);
            movie.setDirectors(directors);
            movie.setGenres(genres);
            Movie saveMovie = movieRepository.save(movie);
            return new ApiResponse("Successfully edited!", true, saveMovie);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }

    }

    @Override
    public ApiResponse deleteMovie(UUID id) {
        try {
            movieRepository.deleteById(id);
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Movie not found!", false);
        }
    }
}
