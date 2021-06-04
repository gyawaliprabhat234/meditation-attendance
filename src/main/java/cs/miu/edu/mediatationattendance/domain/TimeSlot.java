package cs.miu.edu.mediatationattendance.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String code;
	private LocalDate startTime;
	private LocalDate endTime;
	

}
