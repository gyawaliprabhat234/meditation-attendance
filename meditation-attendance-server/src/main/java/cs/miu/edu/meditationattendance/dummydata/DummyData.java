package cs.miu.edu.meditationattendance.dummydata;

import cs.miu.edu.meditationattendance.domain.*;
import cs.miu.edu.meditationattendance.repository.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Registration;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Transactional
@Component
public class DummyData {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;


    public void  createDummyData(){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setCode("AM");
        timeSlot.setStartTime(LocalTime.of(10, 0));
        timeSlot.setEndTime(LocalTime.of(12, 0));
        timeSlotRepository.save(timeSlot);

        Location location = new Location();
        location.setBuildingName("VERILL");
        locationRepository.save(location);

        Faculty faculty = new Faculty();
        faculty.setTitle("MSCS");
        facultyRepository.save(faculty);

        Course ea = new Course();
        ea.setCourseNumber("CS544");

        Course mwa = new Course();
        mwa.setCourseNumber("MWA");
        courseRepository.save(ea);
        courseRepository.save(mwa);

        CourseOffering eaOffering = new CourseOffering();
        eaOffering.setCourse(ea);
        eaOffering.setStartDate(LocalDate.of(2021, 6, 1));
        eaOffering.setEndDate(LocalDate.of(2021, 7, 1));
        eaOffering.setFaculty(faculty);

        ClassSession session = new ClassSession();
        session.setLocation(location);
        session.setTimeslot(timeSlot);
        session.setDate(LocalDate.of(2021, 6, 1));
        session.setCourseOffering(eaOffering);
        classSessionRepository.save(session);

        ClassSession session2 = new ClassSession();
        session2.setLocation(location);
        session2.setTimeslot(timeSlot);
        session2.setDate(LocalDate.of(2021, 5, 2));
        session2.setCourseOffering(eaOffering);
        classSessionRepository.save(session2);

        eaOffering.setSessions(Arrays.asList(session, session2));
        courseOfferingRepository.save(eaOffering);

        Student student = new Student();
        student.setFirstName("Prabhat");
        student.setLastName("Gyawali");
        student.setStudentId("611941");
        student.setBarcode(611941l);
        studentRepository.save(student);

        CourseRegistration registration = new CourseRegistration();
        registration.setRegistrationDateTime(LocalDateTime.now());
        registration.setCourseOffering(eaOffering);
        registration.setStudent(student);
        courseRegistrationRepository.save(registration);

        Attendance attendance1 = new Attendance();
        attendance1.setStudent(student);
        attendance1.setTimeStamp(LocalDateTime.now());
        attendance1.setClassSession(session);
        attendanceRepository.save(attendance1);

        Attendance attendance2 = new Attendance();
        attendance2.setStudent(student);
        attendance2.setTimeStamp(LocalDateTime.now());
        attendance2.setClassSession(session2);
        attendanceRepository.save(attendance2);

    }
}
