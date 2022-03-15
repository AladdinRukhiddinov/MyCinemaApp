package uz.pdp.mycinemaapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.mycinemaapp.entity.Hall;
import uz.pdp.mycinemaapp.entity.MovieSession;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "sessions_halls")
public class SessionsHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Hall hall;
}

