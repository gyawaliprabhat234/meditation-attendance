package cs.miu.edu.mediatationattendance.domain;

import java.time.LocalDate;

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
public class Attendance {
	@Id
	@GeneratedValue
	private String barCode;
	private LocalDate timeStamp;
	@ManyToOne
	@JoinColumn(name="SessionId")
	ClassSession classSession;
	
	

}
