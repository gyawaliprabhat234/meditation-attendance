package cs.miu.edu.meditationattendance.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="timeslot_id")
	private TimeSlot timeslot;
	
	//I think student to be here.
	@ManyToOne
	@JoinColumn(name="offering_id")
	private CourseOffering courseOffering;

	@OneToMany(mappedBy="classSession")
	private List<Attendance> attendances;


}
