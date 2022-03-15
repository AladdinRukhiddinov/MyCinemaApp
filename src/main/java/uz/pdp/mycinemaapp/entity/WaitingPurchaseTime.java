package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "waiting_purchase_times")
public class WaitingPurchaseTime extends AbsEntity {

    @Column(nullable = false)
    private Integer minute;
}
