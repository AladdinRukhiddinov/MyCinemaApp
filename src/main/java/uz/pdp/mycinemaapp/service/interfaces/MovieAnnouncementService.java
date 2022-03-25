package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.MovieAnnouncementDto;

import java.util.UUID;

public interface MovieAnnouncementService {

    ApiResponse getAllMovieAnnouncements(int page, int size);
    ApiResponse getMovieAnnouncement(UUID movieAnnouncementId);
    ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse editMovieAnnouncement(UUID movieAnnouncementId, MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse deleteMovieAnnouncement(UUID movieAnnouncementId);

}
