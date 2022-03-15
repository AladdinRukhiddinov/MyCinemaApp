package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {

}
