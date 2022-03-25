package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.controller.controllerInterfaces.MovieAnnouncementController;
import uz.pdp.mycinemaapp.payload.dtos.MovieAnnouncementDto;
import uz.pdp.mycinemaapp.service.MovieAnnouncementServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovieAnnouncementControllerImpl implements MovieAnnouncementController {

    private final MovieAnnouncementServiceImpl movieAnnouncementService;


    @Override
    public HttpEntity<?> getMovieAnnouncement(UUID id) {
        return ResponseEntity.ok(movieAnnouncementService.getMovieAnnouncement(id));
    }

    @Override
    public HttpEntity<?> getAllMovieAnnouncements(int page, int size) {
        return ResponseEntity.ok(movieAnnouncementService.getAllMovieAnnouncements(page, size));
    }

    @Override
    public HttpEntity<?> addMovieAnnouncement(MovieAnnouncementDto dto) {
        return ResponseEntity.ok(movieAnnouncementService.addMovieAnnouncement(dto));
    }

    @Override
    public HttpEntity<?> editMovieAnnouncement(UUID id, MovieAnnouncementDto dto) {
        return ResponseEntity.ok(movieAnnouncementService.editMovieAnnouncement(id, dto));
    }

    @Override
    public HttpEntity<?> deleteMovieAnnouncement(UUID id) {
        return ResponseEntity.ok(movieAnnouncementService.deleteMovieAnnouncement(id));
    }
}