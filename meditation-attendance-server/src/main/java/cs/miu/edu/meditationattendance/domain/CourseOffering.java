package cs.miu.edu.meditationattendance.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseOffering {
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name="faculty_id")
	private Faculty faculty;

	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="courseOffering")
	private List<ClassSession> listClassSessions;

	//Added
	@OneToMany(mappedBy="courseOffering" , fetch = FetchType.LAZY)
	private List<ClassSession> sessions;

	public CourseOffering(Course course){
		this.course = course;
	}
}
