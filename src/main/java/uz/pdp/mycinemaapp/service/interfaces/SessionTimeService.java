package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.entity.SessionTime;
import uz.pdp.mycinemaapp.payload.ApiResponse;

import java.util.UUID;

public interface SessionTimeService {

    ApiResponse getAllSessionTimes();

    ApiResponse getSessionTime(UUID id);

    ApiResponse addSessionTime(SessionTime date);

    ApiResponse editSessionTime(UUID id, SessionTime date);

    ApiResponse deleteSessionTime(UUID id);

}
