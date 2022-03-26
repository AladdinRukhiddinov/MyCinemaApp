package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.entity.SessionTime;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.SessionTimeDto;

import java.util.UUID;

public interface SessionTimeService {

    ApiResponse getAllSessionTimes();

    ApiResponse getSessionTime(UUID id);

    ApiResponse addSessionTime(SessionTimeDto dto);

    ApiResponse editSessionTime(UUID id, SessionTimeDto dto);

    ApiResponse deleteSessionTime(UUID id);

}
