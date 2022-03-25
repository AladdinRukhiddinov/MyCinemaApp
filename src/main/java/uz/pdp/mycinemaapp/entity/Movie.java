package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Movie extends AbsEntity {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Column(nullable = false)
    private Double min_price;

    @OneToOne
    private Attachment coverImg;

    @OneToOne
    private Attachment trailerVideo;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double budget;

    @ManyToMany
    private List<Actor> actors;

    @ManyToMany
    private List<Director> directors;

    @ManyToMany
    private List<Genre> genres;

    @ManyToOne
    private Distributor distributor;

    @Column(nullable = false)
    private Double distributor_share_in_percent;
}