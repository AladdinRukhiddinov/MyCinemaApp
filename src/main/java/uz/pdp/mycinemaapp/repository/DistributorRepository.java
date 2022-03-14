package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Integer> {
    boolean existsByName(String name);
}
