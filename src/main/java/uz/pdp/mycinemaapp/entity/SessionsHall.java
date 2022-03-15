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
public class SessionsHall extends AbsEntity {

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Hall hall;
}

