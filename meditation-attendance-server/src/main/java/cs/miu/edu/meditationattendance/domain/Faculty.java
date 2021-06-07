package cs.miu.edu.meditationattendance.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Faculty extends User{
	private String title;

	public Faculty(String title, String firstName, String lastName, String email, String username, String password, Set<Role> roles){
		super(1L, firstName,lastName,email,username,password,roles);
		this.title = title;
	}
}
