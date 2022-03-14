package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "purchase_history")
public class PurchaseHistory {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Ticket ticket;

    @OneToOne
    private PayType payType;

    private Date date;
}
