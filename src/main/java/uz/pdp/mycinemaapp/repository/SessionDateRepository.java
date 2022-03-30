package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.mycinemaapp.entity.SessionDate;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface SessionDateRepository extends JpaRepository<SessionDate, UUID> {
    boolean existsByDate(LocalDate date);

    SessionDate getSessionDateByDate(LocalDate date);
}
