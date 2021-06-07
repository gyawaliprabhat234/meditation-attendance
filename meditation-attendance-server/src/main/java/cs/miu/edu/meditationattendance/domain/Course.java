package cs.miu.edu.meditationattendance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length=10, unique = true)
	private String courseNumber;
	private String name;

	@Column(length= 4000)
	private String description;
}
