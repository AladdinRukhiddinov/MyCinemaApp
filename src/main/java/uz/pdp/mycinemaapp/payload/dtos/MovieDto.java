package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class MovieDto {

    @NotBlank(message = "Number can't be empty!")
    private String title;

    @NotBlank(message = "Description can't be empty!")
    private String description;

    @NotBlank(message = "Duration in minute can't be empty!")
    private Integer durationInMin;

    @NotBlank(message = "Minimum price can't be empty!")
    private Double minPrice;

    @NotBlank(message = "Image can't be empty!")
    private UUID coverImgId;

    @NotBlank(message = "Trailer video can't be empty!")
    private UUID trailerVideoId;

    @NotBlank(message = "Release date can't be empty!")
    private String releaseDate;

    private Double budget;

    @NotNull(message = "Distributor can't be empty!")
    private UUID distributorId;

    @NotBlank(message = "Percentage can't be empty!")
    private Double distributorShareInPercentage;

    @NotNull(message = "Actors can't be empty!")
    private List<UUID> actors;

    @NotNull(message = "Directors can't be empty!")
    private List<UUID> directors;

    @NotNull(message = "Genres can't be empty!")
    private List<UUID> genres;

}
