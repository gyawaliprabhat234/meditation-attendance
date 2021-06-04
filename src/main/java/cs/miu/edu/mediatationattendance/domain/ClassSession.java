package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClassSession {
	
	@Id
	@GeneratedValue
	private Integer sessionId;
	@ManyToOne
	@JoinColumn(name="LocationId")
	private Location location;
	@ManyToOne
	@JoinColumn(name="startingTime")
	private TimeSlot timeslot;
	
	
	

}
