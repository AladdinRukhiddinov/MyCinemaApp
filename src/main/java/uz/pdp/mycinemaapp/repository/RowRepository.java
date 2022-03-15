package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.mycinemaapp.entity.Row;
import uz.pdp.mycinemaapp.projection.RowProjection;

import java.util.List;

public interface RowRepository extends JpaRepository<Row,Long> {

    @Query(value = "select cast (r.id as varchar) as id, r.number, h.name as Name from hall_rows r join halls h on h.id = r.hall_id where h.id= :hallId",nativeQuery = true)
    List<RowProjection> ketmon(Long hallId);
}
