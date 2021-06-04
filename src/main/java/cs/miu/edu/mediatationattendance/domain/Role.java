package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
   // private List<String> permission = new ArrayList<>();
    private String permission;
    //need to be added roles or which function can he access.

}
