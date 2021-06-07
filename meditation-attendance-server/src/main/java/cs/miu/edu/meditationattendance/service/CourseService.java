package cs.miu.edu.meditationattendance.service;

import java.util.List;

import cs.miu.edu.meditationattendance.dto.ClassDto;
import cs.miu.edu.meditationattendance.dto.CourseDto;

public interface CourseService {
	
	public List<CourseDto> coursesPastSixMonths() throws Exception;
	public List<ClassDto> coursesWithAttendance() throws Exception;

}
