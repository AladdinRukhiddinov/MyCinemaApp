package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "actors")
public class Actor extends AbsEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String bio;

    @OneToOne
    private Attachment attachment;

    public Actor(String fullName, String bio, Attachment attachment) {
        this.fullName = fullName;
        this.bio = bio;
        this.attachment = attachment;
    }
}
