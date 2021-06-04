package cs.miu.edu.mediatationattendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	@Id
	@GeneratedValue
	private Long id;

	private String barCode;
	private LocalDateTime timeStamp;

	@ManyToOne
	@JoinColumn(name="session_id")
	private ClassSession classSession;

	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	

}
