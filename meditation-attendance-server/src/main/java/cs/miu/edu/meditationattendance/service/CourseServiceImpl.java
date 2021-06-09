package cs.miu.edu.meditationattendance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs.miu.edu.meditationattendance.domain.Attendance;
import cs.miu.edu.meditationattendance.domain.ClassSession;
import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.domain.CourseOffering;
import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.ClassSessionDto;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.repository.CourseOfferingRepository;
import cs.miu.edu.meditationattendance.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseOfferingRepository courseOfferingRepository;

	@Transactional
	public List<CourseDTO> coursesPastSixMonths(Long user) throws Exception {
		try {
			LocalDate date = LocalDate.now().minusMonths(6);
			List<Course> courses = courseRepository.coursesPastSixMonths(date,user);
			List<CourseDTO> coursesDto = convertCoursesDto(courses);
			return coursesDto;
		} catch (Exception e) {
			throw new Exception("Exception: " + e);
		}
	}

	public List<ClassDTO> coursesWithAttendance(Long user) throws Exception {
		try {
			List<CourseOffering> coursesOffering = courseOfferingRepository.coursesWithAttendance(user);
			List<ClassDTO> classesDto = convertClassDto(coursesOffering);
			return classesDto;
		} catch (Exception e) {
			throw new Exception("Exception: " + e);
		}
	}

	public List<CourseDTO> convertCoursesDto(List<Course> courses) {
		List<CourseDTO> coursesDto = new ArrayList<CourseDTO>();
		CourseDTO courseObj;
		for (Course course : courses) {
			courseObj = new CourseDTO();
			courseObj.setCourseNumber(course.getCourseNumber());
			courseObj.setName(course.getName());
			courseObj.setDescription(course.getDescription());
			coursesDto.add(courseObj);
		}
		return coursesDto;
	}

	private List<ClassDTO> convertClassDto(List<CourseOffering> coursesOffering) {
		List<ClassDTO> classesDto = new ArrayList<ClassDTO>();
		ClassDTO classObj;
		for (CourseOffering courseOffering : coursesOffering) {
			classObj = new ClassDTO();
			classObj.setCourseName(courseOffering.getCourse().getName());
			List<ClassSessionDto> classSessions = new ArrayList<ClassSessionDto>();
			ClassSessionDto classSessionObj;
			for (ClassSession classSession : courseOffering.getSessions()) {
				classSessionObj = new ClassSessionDto();
				classSessionObj.setTimeSlot(classSession.getTimeslot());
				List<AttendanceDTO> attendances = new ArrayList<AttendanceDTO>();
				AttendanceDTO attendanceObj;
				for (Attendance attendance : classSession.getAttendances()) {
					attendanceObj = new AttendanceDTO();
					attendanceObj.setTimeStamp(attendance.getTimeStamp());
					attendanceObj.setBarCode(attendance.getStudent().getBarcode());
					attendanceObj.setEmailAddress(attendance.getStudent().getEmailAddress());
//					attendanceObj.setEntryDateTime(attendance.getStudent().getEntryDateTime());
					attendanceObj.setFirstName(attendance.getStudent().getFirstName());
					attendanceObj.setLastName(attendance.getStudent().getLastName());
					//attendanceObj.setStatus(attendance.getStudent().getStatus());
					attendanceObj.setStudentId(attendance.getStudent().getStudentId());
					//attendanceObj.setUserName(attendance.getStudent().getUserName());
					//attendanceObj.setStudent(studentDto);
					attendances.add(attendanceObj);
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
