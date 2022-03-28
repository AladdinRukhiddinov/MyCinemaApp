package uz.pdp.mycinemaapp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.entity.Seat;

import java.util.UUID;

@Projection(types = {Seat.class, Row.class})
public interface AvailableSeatsProjection {

    UUID getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    Boolean getAvailable();

}
