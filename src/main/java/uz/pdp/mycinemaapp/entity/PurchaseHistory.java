package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "purchase_history")
public class PurchaseHistory extends AbsEntity {

    @OneToOne
    private User user;

    @OneToOne
    private Ticket ticket;

//    @OneToOne
//    private PayType payType;
//
//    private LocalDate date;
}
