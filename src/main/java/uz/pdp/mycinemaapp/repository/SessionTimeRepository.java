package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.mycinemaapp.entity.SessionTime;

import java.util.UUID;

@Repository
public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {

}
