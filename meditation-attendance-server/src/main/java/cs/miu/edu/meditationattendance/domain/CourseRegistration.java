package cs.miu.edu.meditationattendance.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
	@Id
	@GeneratedValue
	private Long Id;

	private LocalDateTime registrationDateTime;

	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name="offering_id")
	private CourseOffering courseOffering;

}
