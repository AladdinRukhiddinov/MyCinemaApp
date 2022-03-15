package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
