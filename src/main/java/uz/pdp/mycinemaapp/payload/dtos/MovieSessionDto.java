package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MovieSessionDto {

    private UUID movieAnnouncementId;
    private List<ReservedHallDto> reservedHallDtoList;
}
