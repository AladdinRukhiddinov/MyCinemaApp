package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;
import uz.pdp.mycinemaapp.entity.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "users")
public class User extends AbsEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate date_of_birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions;

}
