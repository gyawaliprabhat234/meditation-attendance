package cs.miu.edu.meditationattendance.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="`Session`")
public class ClassSession {
	@Id
	@GeneratedValue
	private Long sessionId;

	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name="timeslot_id")
	private TimeSlot timeslot;
	
	@ManyToOne
	@JoinColumn(name="courseofering_id")
	private CourseOffering courseOffering;
	
	@OneToMany(mappedBy="classSession")
	private List<Attendance> attendances;
	
	//I think student to be here.
	

}
