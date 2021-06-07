package cs.miu.edu.meditationattendance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs.miu.edu.meditationattendance.domain.Attendance;
import cs.miu.edu.meditationattendance.domain.ClassSession;
import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.domain.CourseOffering;
import cs.miu.edu.meditationattendance.dto.AttendanceDto;
import cs.miu.edu.meditationattendance.dto.ClassDto;
import cs.miu.edu.meditationattendance.dto.ClassSessionDto;
import cs.miu.edu.meditationattendance.dto.CourseDto;
import cs.miu.edu.meditationattendance.dto.StudentDto;
import cs.miu.edu.meditationattendance.repository.CourseOfferingRepository;
import cs.miu.edu.meditationattendance.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseOfferingRepository courseOfferingRepository;

	@Transactional
	public List<CourseDto> coursesPastSixMonths() throws Exception {
		try {
			LocalDate date = LocalDate.now().minusMonths(6);
			List<Course> courses = courseRepository.coursesPastSixMonths(date);
			List<CourseDto> coursesDto = convertCoursesDto(courses);
			return coursesDto;
		} catch (Exception e) {
			throw new Exception("Exception: " + e);
		}
	}

	public List<ClassDto> coursesWithAttendance() throws Exception {
		try {
			List<CourseOffering> coursesOffering = courseOfferingRepository.coursesWithAttendance();
			List<ClassDto> classesDto = convertClassDto(coursesOffering);
			return classesDto;
		} catch (Exception e) {
			throw new Exception("Exception: " + e);
		}
	}

	public List<CourseDto> convertCoursesDto(List<Course> courses) {
		List<CourseDto> coursesDto = new ArrayList<CourseDto>();
		CourseDto courseObj;
		for (Course course : courses) {
			courseObj = new CourseDto();
			courseObj.setCourseNumber(course.getCoureNumber());
			courseObj.setName(course.getName());
			courseObj.setDescription(course.getDescription());
			coursesDto.add(courseObj);
		}
		return coursesDto;
	}

	private List<ClassDto> convertClassDto(List<CourseOffering> coursesOffering) {
		List<ClassDto> classesDto = new ArrayList<ClassDto>();
		ClassDto classObj;
		for (CourseOffering courseOffering : coursesOffering) {
			classObj = new ClassDto();
			classObj.setCourseName(courseOffering.getCourse().getName());
			List<ClassSessionDto> classSessions = new ArrayList<ClassSessionDto>();
			ClassSessionDto classSessionObj;
			for (ClassSession classSession : courseOffering.getListClassSessions()) {
				classSessionObj = new ClassSessionDto();
				classSessionObj.setTimeSlot(classSession.getTimeslot());
				List<AttendanceDto> attendances = new ArrayList<AttendanceDto>();
				AttendanceDto attendanceObj;
				for (Attendance attendance : classSession.getAttendances()) {
					attendanceObj = new AttendanceDto();
					attendanceObj.setTimeStamp(attendance.getTimeStamp());
					StudentDto studentDto = new StudentDto();
					studentDto.setBarcode(attendance.getStudent().getBarcode());
					studentDto.setEmailAddress(attendance.getStudent().getEmailAddress());
					studentDto.setEntryDateTime(attendance.getStudent().getEntryDateTime());
					attendances.add(attendanceObj);
					studentDto.setFirstName(attendance.getStudent().getFirstName());
					studentDto.setLastName(attendance.getStudent().getLastName());
					studentDto.setStatus(attendance.getStudent().getStatus());
					studentDto.setStudentId(attendance.getStudent().getStudentId());
					studentDto.setUserName(attendance.getStudent().getUserName());
					attendanceObj.setStudent(studentDto);
				}
				classSessionObj.setAttendance(attendances);
				classSessions.add(classSessionObj);
			}
			classObj.setClassSessions(classSessions);
			classesDto.add(classObj);
		}
		return classesDto;
	}

}
