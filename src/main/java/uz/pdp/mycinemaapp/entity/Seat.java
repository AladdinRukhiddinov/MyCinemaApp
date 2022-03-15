package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "seats")
public class Seat extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    private Row row;

    @ManyToOne
    private PriceCategory priceCategory;
}
