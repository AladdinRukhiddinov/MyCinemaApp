package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.mycinemaapp.entity.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

    List<Seat> findByRowId(UUID row_id);

    @Query(value = "select * from seats s join hall_rows hr on hr.id = s.row_id\n" +
            "join halls h on h.id = hr.hall_id\n" +
            "where h.id = :hallId",nativeQuery = true)
    List<Seat> findByHallId(UUID hallId);
}
