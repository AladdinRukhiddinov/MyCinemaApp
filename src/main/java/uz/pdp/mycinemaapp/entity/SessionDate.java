package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "session_dates")
public class SessionDate extends AbsEntity {

    @ManyToOne
    private SessionsHall sessionsHall;

    @Column(nullable = false)
    private Date date;
}

