package uz.pdp.mycinemaapp.entity;

import antlr.collections.impl.LList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "session_dates")
public class SessionDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<MovieSession> movieSession;

    @Column(nullable = false)
    private Date date;
}

