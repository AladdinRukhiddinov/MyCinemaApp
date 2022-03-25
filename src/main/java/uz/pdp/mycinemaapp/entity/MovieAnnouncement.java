package uz.pdp.mycinemaapp.entity;


import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class MovieAnnouncement extends AbsEntity {

    @OneToOne
    private Movie movie;

    @Column(nullable = false)
    private Boolean isActive;
}
