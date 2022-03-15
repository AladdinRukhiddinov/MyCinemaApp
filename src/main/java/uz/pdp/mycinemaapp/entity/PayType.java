package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "pay_types")
public class PayType extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private Double commission_fee_in_percent;
}
