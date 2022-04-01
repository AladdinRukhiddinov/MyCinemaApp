package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "session_times")
public class SessionTime extends AbsEntity {

    @Column(nullable = false)
    private LocalTime time;

}

