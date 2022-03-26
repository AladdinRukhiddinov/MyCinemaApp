package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.NightSessionAddFee;

import java.util.UUID;

public interface NightSessionAddFeeRepository extends JpaRepository<NightSessionAddFee, UUID> {
    boolean existsByTimeId(UUID time_id);

    boolean existsByTimeIdAndPercentageAndIdNot(UUID time_id, Double percentage, UUID id);
}
