package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
