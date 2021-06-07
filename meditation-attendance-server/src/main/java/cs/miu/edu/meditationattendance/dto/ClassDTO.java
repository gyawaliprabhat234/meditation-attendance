package cs.miu.edu.meditationattendance.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDTO {
	
	private String courseName;
	private List<ClassSessionDto> classSessions;
	
}
