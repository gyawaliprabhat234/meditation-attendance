package cs.miu.edu.meditationattendance.service;

import java.util.List;

import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;

public interface CourseService {
	
	public List<CourseDTO> coursesPastSixMonths(Long user) throws Exception;
	public List<ClassDTO> coursesWithAttendance(Long user) throws Exception;

}
