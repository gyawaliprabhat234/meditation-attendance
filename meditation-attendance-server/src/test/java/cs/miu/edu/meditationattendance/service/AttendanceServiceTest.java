package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.*;
import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.AttendanceRepository;
import cs.miu.edu.meditationattendance.repository.ClassSessionRepository;
import cs.miu.edu.meditationattendance.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AttendanceServiceTest {
    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private ClassSessionRepository classSessionRepository;

    private AttendanceService attendanceService;

    @Mock
    private StudentRepository studentRepository;

    @Captor
    private ArgumentCaptor<Attendance> attendanceArgumentCaptor;


    private Attendance attendance;
    private Attendance returnSavedAttendance;
    private AttendanceDTO attendanceDTO;
    private LocalDateTime currentTime;
    private Student student;
    private ClassSession session;

    @BeforeEach
    void setUp() {
        attendanceService = new AttendanceServiceImpl(attendanceRepository, classSessionRepository, studentRepository);
        student = new Student();
        student.setId(123l);
        student.setFirstName("Prabhat");
        student.setLastName("Gyawali");
        student.setStudentId("611941");
        student.setBarcode(611941l);
        currentTime = LocalDateTime.now();

        CourseOffering eaOffering = new CourseOffering();
        eaOffering.setCourse(new Course(1l, "CS544", null, null));
        eaOffering.setStartDate(LocalDate.of(2021, 6, 1));
        eaOffering.setEndDate(LocalDate.of(2021, 7, 1));

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setCode("AM");
        timeSlot.setStartTime(LocalTime.of(10, 0));
        timeSlot.setEndTime(LocalTime.of(12, 0));

        session = new ClassSession();
        session.setSessionId(234l);
        session.setDate(LocalDate.of(2021, 6, 1));
        session.setCourseOffering(eaOffering);
        session.setTimeslot(timeSlot);

        attendance = new Attendance();
        attendance.setTimeStamp(currentTime);
        attendance.setBarCode(611941l);
        attendance.setStudent(student);
        attendance.setClassSession(session);

        returnSavedAttendance = new Attendance();
        returnSavedAttendance.setTimeStamp(currentTime);
        returnSavedAttendance.setBarCode(611941l);
        returnSavedAttendance.setStudent(student);
        returnSavedAttendance.setClassSession(session);

        attendanceDTO = new AttendanceDTO();
        attendanceDTO.setBarCode(611941l);
        attendanceDTO.setTimeStamp(currentTime);
        attendanceDTO.setSessionId(234l);

    }

    @Test
    void findAllAttendanceByStudentId() {
    }

    @Test
    void when_saveAttendance_is_invoked_then_attendance_should_be_saved() throws ResourceNotFoundException {

        Mockito.when(studentRepository.findByBarcode(611941l)).thenReturn(Optional.of(student));
        Mockito.when(classSessionRepository.findById(234l)).thenReturn((Optional.of(session)));
        Mockito.when(attendanceRepository.save(Mockito.any())).thenReturn(returnSavedAttendance);
        AttendanceDTO savedAttendanceDTO = attendanceService.saveAttendance(attendanceDTO);
        Mockito.verify(attendanceRepository, Mockito.times(1))
                .save(attendanceArgumentCaptor.capture());
        Assertions.assertThat(attendanceArgumentCaptor.getValue().getBarCode()).isEqualTo(611941l);
        Assertions.assertThat(attendanceArgumentCaptor.getValue().getBarCode()).isEqualTo(savedAttendanceDTO.getBarCode());
    }

    @Test
    void when_aveAttendance_is_called_with_no_existing_barcode_should_throw_exception() throws ResourceNotFoundException {

        Mockito.when(studentRepository.findByBarcode(611942l)).thenReturn(Optional.of(student));
        Mockito.when(classSessionRepository.findById(234l)).thenReturn((Optional.of(session)));
        Mockito.when(attendanceRepository.save(Mockito.any())).thenReturn(returnSavedAttendance);
        assertThatThrownBy(()-> attendanceService.saveAttendance(attendanceDTO)).isInstanceOf(ResourceNotFoundException.class);
        Mockito.verify(attendanceRepository, Mockito.never())
                .save(attendanceArgumentCaptor.capture());

    }

    @Test
    void deleteAttendance() {
    }
}