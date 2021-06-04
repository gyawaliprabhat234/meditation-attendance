package cs.miu.edu.mediatationattendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	

}
