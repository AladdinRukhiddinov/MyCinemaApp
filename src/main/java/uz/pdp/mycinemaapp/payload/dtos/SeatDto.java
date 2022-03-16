package uz.pdp.mycinemaapp.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDto {

    @NotBlank(message = "Number can't be empty!")
    private Integer number;

    @NotBlank(message = "Row can't be empty!")
    private UUID rowId;

    @NotBlank(message = "Price category can't be empty!")
    private UUID priceCategoryId;

}
