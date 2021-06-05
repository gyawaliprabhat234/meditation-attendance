package cs.miu.edu.meditationattendance.domain;

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
	
	//I think student to be here.
	

}
