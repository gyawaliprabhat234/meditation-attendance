package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
private Integer id;
private String name;
private String permission;

}
