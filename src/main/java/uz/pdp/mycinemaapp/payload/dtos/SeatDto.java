package uz.pdp.mycinemaapp.payload.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDto {

    @NotBlank(message = "Number can't be empty!")
    private Integer number;

    @NotNull(message = "Row can't be empty!")
    private UUID rowId;

    @NotNull(message = "Price category can't be empty!")
    private UUID priceCategoryId;

}
