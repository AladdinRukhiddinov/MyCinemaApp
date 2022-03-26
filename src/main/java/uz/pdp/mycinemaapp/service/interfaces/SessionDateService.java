package uz.pdp.mycinemaapp.service.interfaces;

import uz.pdp.mycinemaapp.entity.SessionDate;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.SessionDateDto;

import java.util.UUID;

public interface SessionDateService {

    ApiResponse getAllSessionDates();

    ApiResponse getSessionDate(UUID id);

    ApiResponse addSessionDate(SessionDateDto dto);

    ApiResponse editSessionDate(UUID id, SessionDateDto dto);

    ApiResponse deleteSessionDate(UUID id);

}
