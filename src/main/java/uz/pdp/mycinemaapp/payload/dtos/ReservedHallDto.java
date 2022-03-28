package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReservedHallDto {

    private UUID hallId;

    private String startDate;

    private String endDate;

    private List<String> startTimeList;
}
