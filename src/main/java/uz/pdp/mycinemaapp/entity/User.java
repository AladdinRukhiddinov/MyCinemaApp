package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.mycinemaapp.entity.enums.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date date_of_birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions;

}
