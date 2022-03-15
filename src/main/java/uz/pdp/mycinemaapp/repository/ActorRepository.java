package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Actor;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
}
