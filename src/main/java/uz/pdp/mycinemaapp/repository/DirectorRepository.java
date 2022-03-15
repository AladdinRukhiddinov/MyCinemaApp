package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Director;

public interface DirectorRepository extends JpaRepository<Director,Long> {

}
