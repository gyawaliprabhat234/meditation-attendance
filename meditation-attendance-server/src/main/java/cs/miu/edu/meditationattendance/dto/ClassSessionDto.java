package cs.miu.edu.meditationattendance.dto;

import java.util.List;

import cs.miu.edu.meditationattendance.domain.TimeSlot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassSessionDto {
	
	private TimeSlot timeSlot;
	private List<AttendanceDto> attendance;

}
