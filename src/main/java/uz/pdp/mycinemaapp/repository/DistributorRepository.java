package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Distributor;

import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
    boolean existsByName(String name);
}
