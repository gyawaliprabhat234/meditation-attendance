package cs.miu.edu.meditationattendance.dto;

import java.time.LocalDateTime;

import cs.miu.edu.meditationattendance.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
	
	private LocalDateTime timeStamp;
	private StudentDto student;

}
