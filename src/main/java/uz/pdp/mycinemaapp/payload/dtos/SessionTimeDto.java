package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SessionTimeDto {

    @NotBlank(message = "TIME_REQUIRED")
    private String time;
}
