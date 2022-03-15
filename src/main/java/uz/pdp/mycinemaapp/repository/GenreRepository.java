package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Genre;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

}
