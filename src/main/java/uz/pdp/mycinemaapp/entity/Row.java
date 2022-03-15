package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seatList;

    @ManyToOne
    private Hall hall;

    public Row(Integer number, Hall hall) {
        this.number = number;
        this.hall = hall;
    }
}
