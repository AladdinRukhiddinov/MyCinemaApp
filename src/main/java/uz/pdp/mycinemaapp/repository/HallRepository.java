package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Hall;

import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {

}
