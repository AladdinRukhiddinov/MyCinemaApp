package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "session_dates")
public class SessionDate extends AbsEntity {

    @Column(nullable = false)
    private LocalDate date;

}

