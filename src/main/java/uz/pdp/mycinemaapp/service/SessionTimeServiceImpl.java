package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.SessionTime;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.repository.SessionTimeRepository;
import uz.pdp.mycinemaapp.service.interfaces.SessionTimeService;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionTimeServiceImpl implements SessionTimeService {

    private SessionTimeRepository sessionTimeRepository;

    @Override
    public ApiResponse getAllSessionTimes() {
        return new ApiResponse(MessageService.getMessage("SUCCESS"),true,sessionTimeRepository.findAll());
    }

    @Override
    public ApiResponse getSessionTime(UUID id) {
        SessionTime sessionTime = sessionTimeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("TIME_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return new ApiResponse(MessageService.getMessage("SUCCESS"),true,sessionTime);
    }

    @Override
    public ApiResponse addSessionTime(SessionTime date) {
        try {
            SessionTime newTime = new SessionTime(date.getTime());
            SessionTime save = sessionTimeRepository.save(newTime);
            return new ApiResponse(MessageService.getMessage("TIME_SAVED"),true,save);
        } catch (RestException e){
            throw new RestException(MessageService.getMessage("MY_ERROR"),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ApiResponse editSessionTime(UUID id, SessionTime date) {
        SessionTime sessionTime = sessionTimeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("TIME_NOT_FOUND"), HttpStatus.NOT_FOUND));
        try{
            sessionTime.setTime(date.getTime());
            SessionTime save = sessionTimeRepository.save(sessionTime);
            return new ApiResponse(MessageService.getMessage("TIME_EDITED"),true,save);
        } catch (RestException e){
            throw new RestException(MessageService.getMessage("TIME_ALREADY_EXISTS"),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ApiResponse deleteSessionTime(UUID id) {
        SessionTime sessionTime = sessionTimeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("TIME_NOT_FOUND"), HttpStatus.NOT_FOUND));
        sessionTimeRepository.delete(sessionTime);
        return new ApiResponse(MessageService.getMessage("TIME_DELETED"),true);
    }
}
