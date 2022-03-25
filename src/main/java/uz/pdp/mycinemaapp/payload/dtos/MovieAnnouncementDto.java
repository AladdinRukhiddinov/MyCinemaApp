package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MovieAnnouncementDto {
    @NotNull(message = "MOVIE_ANNOUNCEMENT_REQUIRED")
    private UUID movieId;

    @NotBlank(message = "IS_ACTIVE_REQUIRED")
    private Boolean isActive;
}
