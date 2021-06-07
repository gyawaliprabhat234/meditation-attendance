package cs.miu.edu.meditationattendance.service;

import java.util.List;

import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;

public interface CourseService {
	
	public List<CourseDTO> coursesPastSixMonths() throws Exception;
	public List<ClassDTO> coursesWithAttendance() throws Exception;

}
