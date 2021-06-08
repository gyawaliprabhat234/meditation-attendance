package cs.miu.edu.meditationattendance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Role`")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Role(String role) {
        this.name = role;
    }
}
