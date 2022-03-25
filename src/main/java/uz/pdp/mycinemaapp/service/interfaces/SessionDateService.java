package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.entity.SessionDate;
import uz.pdp.mycinemaapp.payload.ApiResponse;

import java.util.UUID;

public interface SessionDateService {

    ApiResponse getAllSessionDates();

    ApiResponse getSessionDate(UUID id);

    ApiResponse addSessionDate(SessionDate date);

    ApiResponse editSessionDate(UUID id, SessionDate date);

    ApiResponse deleteSessionDate(UUID id);

}
