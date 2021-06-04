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
public class CourseOffering {
	
	private Integer id;
	private LocalDate startDate;
	private LocalDate endDate;
	@ManyToOne
	@JoinColumn(name="courseId")
	private Course course;

}
