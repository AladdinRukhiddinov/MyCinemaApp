package uz.pdp.mycinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hall_rows")
@JsonIgnoreProperties(value = {"seatList"})
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @JsonIgnore
    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seatList;

    @ManyToOne
    private Hall hall;

    public Row(Integer number, Hall hall) {
        this.number = number;
        this.hall = hall;
    }
}
