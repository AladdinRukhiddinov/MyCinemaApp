package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.SessionTime;

import java.util.UUID;

public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {

}
