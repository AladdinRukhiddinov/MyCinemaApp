package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "session_times")
public class SessionTime extends AbsEntity {

    @Column(nullable = false)
    private Timestamp time;

    @ManyToOne
    private SessionDate sessionDate;
}

