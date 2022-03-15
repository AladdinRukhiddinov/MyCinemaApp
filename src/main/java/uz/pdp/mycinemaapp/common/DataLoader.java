package uz.pdp.mycinemaapp.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.repository.HallRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final HallRepository hallRepository;

    @Value(("${spring.sql.init.mode}"))
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            Hall hall = new Hall();
            hall.setName("Vip HALL");
            hall.setVip_additional_fee_in_percent(5.0);

            List<Row> rowList = new ArrayList<>(Arrays.asList(
                    new Row(1,hall),
                    new Row(2,hall),
                    new Row(3,hall)
            ));

            hall.setRowList(rowList);
            hallRepository.save(hall);

        }
    }
}
