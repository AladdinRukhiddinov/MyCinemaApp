package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "seats")
@Table(uniqueConstraints = @UniqueConstraint(name = "rowAndNumber",columnNames = {"number", "row_id"}))
public class Seat extends AbsEntity {

    @Column(nullable = false, name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;

    @ManyToOne
    @JoinColumn(name = "price_category_id")
    private PriceCategory priceCategory;

    public Seat(Integer number, UUID rowId, UUID priceCategoryId) {
        super();
    }
}
