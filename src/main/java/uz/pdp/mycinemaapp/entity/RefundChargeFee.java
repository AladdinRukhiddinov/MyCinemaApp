package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "refund_charge_fees")
public class RefundChargeFee extends AbsEntity {

    @Column(nullable = false)
    private Integer intervalMinutes;

    @Column(nullable = false)
    private Double percentage;

}
