package cs.miu.edu.meditationattendance.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import cs.miu.edu.meditationattendance.domain.Attendance;
import cs.miu.edu.meditationattendance.domain.ClassSession;
import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.domain.CourseOffering;
import cs.miu.edu.meditationattendance.domain.Faculty;
import cs.miu.edu.meditationattendance.domain.Location;
import cs.miu.edu.meditationattendance.domain.Student;
import cs.miu.edu.meditationattendance.domain.TimeSlot;
import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.repository.CourseOfferingRepository;
import cs.miu.edu.meditationattendance.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CourseServiceTest {

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private CourseOfferingRepository courseOfferingRepository;

	private CourseService courseService;

	private Course course;
	private CourseOffering courseOffering;
	private ClassSession classSession;
	private Location location;
	private Attendance attendance;
	private Student student;
	private Faculty faculty;
	private List<Course> courses;
	private List<CourseOffering> coursesOffering;
	private List<ClassSession> classSessions;
	private List<Attendance> attendances;
	private LocalDate date;
	private TimeSlot timeSlot;
	private LocalDateTime currentTime;

	@BeforeEach
	void setUp() {

		date = LocalDate.now().minusMonths(6);

		courseService = new CourseServiceImpl(courseRepository, courseOfferingRepository);
		faculty = new Faculty();
		faculty.setId(1L);
		
		currentTime = LocalDateTime.now();

		student = new Student();
		student.setId(124l);
		student.setFirstName("Anderson");
		student.setLastName("Arias");
		student.setStudentId("000-61-2941");
		student.setBarcode(128367l);

		course = new Course();
		course.setCourseNumber("1034");
		course.setDescription("Test1");
		course.setName("Test1");

		courses = new ArrayList<Course>();
		courses.add(course);

		course = new Course();
		course.setCourseNumber("4509");
		course.setDescription("Test2");
		course.setName("Test2");

		courses.add(course);
		
		location = new Location();
		location.setBuildingName("140");
		location.setDescription("TestLocation");
		location.setLocationId(1);
		location.setRoomName("316");

		timeSlot = new TimeSlot();
		timeSlot.setCode("AM");
		timeSlot.setStartTime(LocalTime.of(10, 0));
		timeSlot.setEndTime(LocalTime.of(12, 0));
		
		attendance = new Attendance();
        attendance.setTimeStamp(currentTime);
        attendance.setBarCode(128367l);
        attendance.setStudent(student);
        attendance.setClassSession(classSession);
        attendances = new ArrayList<Attendance>();
        attendances.add(attendance);
		
		classSession = new ClassSession();
		classSession.setCourseOffering(courseOffering);
		classSession.setDate(date);
		classSession.setSessionId(1L);
		classSession.setLocation(location);
		classSession.setTimeslot(timeSlot);
		classSession.setAttendances(attendances);
		classSessions = new ArrayList<ClassSession>();
		classSessions.add(classSession);

		courseOffering = new CourseOffering();
		courseOffering.setFaculty(faculty);
		courseOffering.setCourse(course);
		courseOffering.setEndDate(date);
		courseOffering.setStartDate(date);
		courseOffering.setId(1L);
		courseOffering.setSessions(classSessions);
		coursesOffering = new ArrayList<CourseOffering>();
		coursesOffering.add(courseOffering);

	}

	@Test
	void findCourses_by_faculty() throws Exception {
		Mockito.when(courseRepository.coursesPastSixMonths(date, faculty.getId())).thenReturn(courses);
		List<CourseDTO> listCourses = courseService.coursesPastSixMonths(faculty.getId());
		Assertions.assertThat(listCourses.size()).isEqualTo(2);
		assertThat(listCourses.get(0).getCourseNumber()).isEqualTo("1034");
		assertThat(listCourses.get(1).getCourseNumber()).isEqualTo("4509");
	}
	
	@Test
	void findClassAttendance_by_faculty() throws Exception{
		Mockito.when(courseOfferingRepository.coursesWithAttendance(faculty.getId())).thenReturn(coursesOffering);
		List<ClassDTO> classes = courseService.coursesWithAttendance(faculty.getId());
		Assertions.assertThat(classes.size()).isEqualTo(1);
		assertThat(classes.get(0).getCourseName()).isEqualTo("Test2");
	}

}
