package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Movie extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @OneToMany
    private List<Attachment> cover_image;

    @OneToMany
    private List<Attachment> trailer_video;

    @OneToMany
    private List<Director> directors;

    @ManyToMany
    private List<Genre> genres;

    @Column(nullable = false)
    private Double min_price;

    @OneToOne
    private Distributor distributor;

    @Column(nullable = false)
    private Double distributor_share_in_percent;
}
