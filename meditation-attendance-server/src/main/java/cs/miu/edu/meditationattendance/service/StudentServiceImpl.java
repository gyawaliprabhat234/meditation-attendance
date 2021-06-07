package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.ClassSession;
import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.domain.Location;
import cs.miu.edu.meditationattendance.domain.Student;
import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.ClassSessionRepository;
import cs.miu.edu.meditationattendance.repository.CourseRegistrationRepository;
import cs.miu.edu.meditationattendance.repository.LocationRepository;
import cs.miu.edu.meditationattendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public StudentDTO findStudentById(String studentId) throws ResourceNotFoundException {
        Optional<Student> student = studentRepository.findByStudentId(studentId);
        if(!student.isPresent()) throw new ResourceNotFoundException("Student not found");
        return mapToStudentDTO(student.get());
    }

    @Override
    public List<AttendanceDTO> findAllStudentByLocation(LocationDTO locationDTO) throws ResourceNotFoundException {
        Optional<Location> location =  locationRepository.findByBuildingNameAndRoomName(locationDTO.getBuildingName(), locationDTO.getRoomName());
        if(!location.isPresent())
            throw new ResourceNotFoundException("Location is invalid");
        Optional<ClassSession> classSession = classSessionRepository.findClassSessionByLocationIdAndCurrentTime(location.get().getLocationId(), LocalDate.now(), LocalTime.now(), LocalTime.now().plusMinutes(30));
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

    public Optional<Course> findCurrentCourseByStudentId(String studentId , LocalDate date){
        Optional<Course> course = courseRegistrationRepository.findCurrentCourseByStudentId(studentId, date);
        return course;
    }

    private StudentDTO mapToStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setStatus(student.getStatus());
        studentDTO.setEmailAddress(student.getEmailAddress());
        studentDTO.setEntryDateTime(student.getEntryDateTime());
        Optional<Course> currentCourse = this.findCurrentCourseByStudentId(student.getStudentId(), LocalDate.now());
        if(currentCourse.isPresent()) {
            studentDTO.setCurrentCourse(currentCourse.get().getCourseNumber());
        }
        return studentDTO;
    }
}
