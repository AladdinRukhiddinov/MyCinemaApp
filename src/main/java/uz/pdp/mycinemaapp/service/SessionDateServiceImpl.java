package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.SessionDate;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.repository.SessionDateRepository;
import uz.pdp.mycinemaapp.service.interfaces.SessionDateService;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionDateServiceImpl implements SessionDateService {

    private final SessionDateRepository sessionDateRepository;

    @Override
    public ApiResponse getAllSessionDates() {
        return new ApiResponse(MessageService.getMessage("SUCCESS"),true,sessionDateRepository.findAll());
    }

    @Override
    public ApiResponse getSessionDate(UUID id) {
        SessionDate sessionDate = sessionDateRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("DATE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return new ApiResponse(MessageService.getMessage("SUCCESS"),true,sessionDate);
    }

    @Override
    public ApiResponse addSessionDate(SessionDate date) {
        if (sessionDateRepository.existsByDate(date.getDate()))
            throw new RestException(MessageService.getMessage("DATE_ALREADY_EXISTS"),HttpStatus.CONFLICT);

            SessionDate save = sessionDateRepository.save(new SessionDate(date.getDate()));
            return new ApiResponse(MessageService.getMessage("DATE_SAVED"),true,save);

    }

    @Override
    public ApiResponse editSessionDate(UUID id, SessionDate date) {
        SessionDate sessionDate = sessionDateRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("DATE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        if (sessionDateRepository.existsByDate(date.getDate()))
            throw new RestException(MessageService.getMessage("DATE_ALREADY_EXISTS"),HttpStatus.CONFLICT);

            sessionDate.setDate(date.getDate());
            SessionDate save = sessionDateRepository.save(sessionDate);
            return new ApiResponse(MessageService.getMessage("DATE_EDITED"),true,save);

    }

    @Override
    public ApiResponse deleteSessionDate(UUID id) {
        SessionDate sessionDate = sessionDateRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("DATE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        sessionDateRepository.delete(sessionDate);
        return new ApiResponse(MessageService.getMessage("DATE_DELETED"),true);
    }
}
