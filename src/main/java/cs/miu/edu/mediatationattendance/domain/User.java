package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`User`")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Role> role = new HashSet<>();

//    public User(Role role) {
//        this.role.add(role);
//    }
}
