package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "night_session_add_fee")
public class NightSessionAddFee extends AbsEntity {

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private Date date;
}
