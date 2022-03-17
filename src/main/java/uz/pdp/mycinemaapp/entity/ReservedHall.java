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
@Entity(name = "sessions_halls")
public class ReservedHall extends AbsEntity {

    @ManyToOne
    private MovieSchedule movieSchedule;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private SessionDate startDate;

    @ManyToOne
    private SessionTime startTime;

    @ManyToOne
    private SessionTime endTime;
}

