package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.*;
import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    @Transactional
    public StudentDTO findStudentById(String studentId) throws ResourceNotFoundException {
        Optional<Student> student = studentRepository.findByStudentId(studentId);
        if(!student.isPresent()) throw new ResourceNotFoundException("Student not found");
        return mapToStudentDTO(student.get());
    }

    @Override
    @Transactional
    public List<AttendanceDTO> findAllStudentByLocation(LocationDTO locationDTO) throws ResourceNotFoundException {
        Optional<Location> location =  locationRepository.findByBuildingNameAndRoomName(locationDTO.getBuildingName(), locationDTO.getRoomName());
        if(!location.isPresent())
            throw new ResourceNotFoundException("Location is invalid");
        //TODO testing purpose
        LocalDate date = LocalDate.of(2021,6,1);
        LocalTime time = LocalTime.of(9,55);

        Optional<ClassSession> classSession = classSessionRepository.findClassSessionByLocationIdAndCurrentTime(location.get().getLocationId(), date, time, time.plusMinutes(30));
        if(!classSession.isPresent())
            throw new ResourceNotFoundException("No class session is found");
        List<Student> students = studentRepository.findAllStudentBySessionId(classSession.get().getSessionId());
        List<AttendanceDTO> attendanceDTOS = students.stream().map(x-> {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setBarCode(x.getBarcode());
            attendanceDTO.setSessionId(classSession.get().getSessionId());
            return attendanceDTO;
        }).collect(Collectors.toList());
        return attendanceDTOS;
    }
    @Transactional
    public List<AttendanceDTO> findAllAttendanceByCourseNumber(String courseNumber,String studentId) throws ResourceNotFoundException {
        Optional<Course> course = courseRepository.findCourseByCourseNumber(courseNumber);
        if(!course.isPresent())
            throw new ResourceNotFoundException("Course not found");
        List<Attendance> attendances = attendanceRepository.findAllAttendanceByCourseNumber(courseNumber, studentId);
        return attendances.stream().map(attendance -> mapToAttendanceDTO(attendance)).collect(Collectors.toList());
    }

    @Transactional
    public List<CourseDTO> findAllCoursesByStudentId(String studentId) {
        List<Course> courses = courseRegistrationRepository.findCoursesByStudentId(studentId);
        return courses.stream().map(course -> mapToCourseDTO(course)).collect(Collectors.toList());
    }

    @Override
    public Student findStudentByUserId(Long id) {
        return studentRepository.findById(id).get();

    }

    public Optional<Course> findCurrentCourseByStudentId(String studentId , LocalDate date){
        Optional<Course> course = courseRegistrationRepository.findCurrentCourseByStudentId(studentId, date);
        return course;
    }

    private StudentDTO mapToStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setBarcode(student.getBarcode());
        studentDTO.setId(student.getId());
        studentDTO.setUserName(student.getUserName());
        studentDTO.setStatus(student.getStatus());
        studentDTO.setEmailAddress(student.getEmailAddress());
        studentDTO.setEntryDateTime(student.getEntryDateTime());
        Optional<Course> currentCourse = this.findCurrentCourseByStudentId(student.getStudentId(), LocalDate.now());
        if(currentCourse.isPresent()) {
            studentDTO.setCurrentCourse(currentCourse.get().getName());
        }
        return studentDTO;
    }
    private AttendanceDTO mapToAttendanceDTO(Attendance attendance) {
        Student student = attendance.getStudent();
        CourseOffering courseOffering = attendance.getClassSession().getCourseOffering();
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setSessionId(attendance.getClassSession().getSessionId());
        attendanceDTO.setStudentId(student.getStudentId());
        attendanceDTO.setFirstName((student.getFirstName()));
        attendanceDTO.setLastName((student.getLastName()));
        attendanceDTO.setBarCode(student.getBarcode());
        attendanceDTO.setTimeStamp(attendance.getTimeStamp());
        attendanceDTO.setTimeSlotCode(attendance.getClassSession().getTimeslot().getCode());
        attendanceDTO.setCourseEndDate(courseOffering.getEndDate());
        attendanceDTO.setCourseStartDate(courseOffering.getStartDate());
        attendanceDTO.setCourseName(courseOffering.getCourse().getName());
        return attendanceDTO;
    }

    private CourseDTO mapToCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseNumber(course.getCourseNumber());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());

        return courseDTO;
    }


}
