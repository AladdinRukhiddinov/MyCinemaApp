package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "waiting_purchase_times")
public class WaitingPurchaseTime {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer minute;
}
