package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class NightSessionAddFeeDto {

    @NotBlank(message = "PERCENTAGE_REQUIRED")
    private Double percentage;

    @NotBlank(message = "TIME_REQUIRED")
    private UUID timeId;
}
