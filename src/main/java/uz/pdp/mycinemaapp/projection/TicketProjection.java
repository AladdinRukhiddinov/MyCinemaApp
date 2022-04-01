package uz.pdp.mycinemaapp.projection;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface TicketProjection {
    UUID getId();

    Double getPrice();

    String getTitle();

    Integer getSeatNumber();

    Integer getRowNumber();

    String getHallName();

    LocalDate getSessionDate();

    LocalTime getSessionTime();

}
