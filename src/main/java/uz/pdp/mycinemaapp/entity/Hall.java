package uz.pdp.mycinemaapp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "halls")
public class Hall extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private Double vip_additional_fee_in_percent;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rowList;

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, Double vip_additional_fee_in_percent) {
        this.name = name;
        this.vip_additional_fee_in_percent = vip_additional_fee_in_percent;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "name='" + name + '\'' +
                ", vip_additional_fee_in_percent=" + vip_additional_fee_in_percent +
                '}';
    }
}
