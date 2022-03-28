package uz.pdp.mycinemaapp.projection;


import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

public interface TicketProjection {
    UUID getId();

    String getTitle();



    // TODO: 3/28/2022  ...
}
