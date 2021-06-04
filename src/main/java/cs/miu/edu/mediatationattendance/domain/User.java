package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User {

	@Id
	//@SequenceGenerator(name="pe")
	// (000-xx-yyyy)
	private Integer id;
	private String firstName;
	private String lastName;
	private String barCode;
	private String emailAddress;
	private String userName;
}
