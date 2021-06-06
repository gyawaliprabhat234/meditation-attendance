package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.dto.AttendanceDto;
import cs.miu.edu.meditationattendance.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
     List<CourseDto> getCourses(Long studentId);
     List<AttendanceDto> getAttendance(Long studentId);
}
