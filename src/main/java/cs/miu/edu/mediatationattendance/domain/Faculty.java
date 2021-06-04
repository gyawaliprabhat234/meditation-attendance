package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Faculty extends User{
	private String title;

}
