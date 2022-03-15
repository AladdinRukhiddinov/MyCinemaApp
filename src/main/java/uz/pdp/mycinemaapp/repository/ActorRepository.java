package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long> {
}
