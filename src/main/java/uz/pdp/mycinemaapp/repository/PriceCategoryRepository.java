package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.PriceCategory;

import java.util.UUID;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, UUID> {

}
