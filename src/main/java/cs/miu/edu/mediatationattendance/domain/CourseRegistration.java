package cs.miu.edu.mediatationattendance.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
	private LocalDate registrationDateTime;
	@ManyToOne
	@JoinColumn(name="studentId")
	private Student student;
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseOffering courseOffering;

}
