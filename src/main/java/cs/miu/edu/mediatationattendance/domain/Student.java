package cs.miu.edu.mediatationattendance.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User{
	public String studentId;
	private String status;
	private LocalDateTime entryDateTime;
	private Long barcode;
	
}
