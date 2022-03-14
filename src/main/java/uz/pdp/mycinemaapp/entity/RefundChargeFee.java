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
@Entity(name = "refund_charge_fees")
public class RefundChargeFee {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer intervalMinutes;

    @Column(nullable = false)
    private Double percentage;

}
