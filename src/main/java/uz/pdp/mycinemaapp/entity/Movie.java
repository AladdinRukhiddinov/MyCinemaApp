package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
