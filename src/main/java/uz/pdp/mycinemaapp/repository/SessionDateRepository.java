package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.SessionDate;

import java.util.UUID;

public interface SessionDateRepository extends JpaRepository<SessionDate, UUID> {

}
