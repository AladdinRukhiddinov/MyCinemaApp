package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Movie_sessions")
public class MovieSession extends AbsEntity {

    @ManyToOne
    private MovieAnnouncement movieAnnouncement;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private SessionDate startDate;

    @ManyToOne
    private SessionTime startTime;

    @ManyToOne
    private SessionTime endTime;

}

