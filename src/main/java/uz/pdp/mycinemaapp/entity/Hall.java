package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double vip_additional_fee_in_percent;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rowList;

    public Hall(String name, Double vip_additional_fee_in_percent, List<Row> rowList) {
        this.name = name;
        this.vip_additional_fee_in_percent = vip_additional_fee_in_percent;
        this.rowList = rowList;
    }
}
