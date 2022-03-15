package uz.pdp.mycinemaapp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.entity.Row;

@Projection(types = {Row.class, Hall.class})
public interface RowProjection {

    Long getId();

    Integer getNumber();

    String getName();
}
