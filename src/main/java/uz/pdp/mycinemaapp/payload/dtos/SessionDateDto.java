package uz.pdp.mycinemaapp.payload.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SessionDateDto {

    @NotBlank(message = "DATE_REQUIRED")
    private String date;
}
