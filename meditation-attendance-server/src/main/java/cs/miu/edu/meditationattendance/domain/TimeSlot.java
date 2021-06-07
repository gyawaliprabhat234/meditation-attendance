package cs.miu.edu.meditationattendance.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
public class TimeSlot {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String code;
	private LocalTime startTime;
	private LocalTime endTime;
	

}
