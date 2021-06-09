package cs.miu.edu.meditationattendance.dummydata;

import cs.miu.edu.meditationattendance.domain.*;
import cs.miu.edu.meditationattendance.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;

@Transactional
@Component
public class DummyData {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministratorRepository adminRepository;

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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setCode("AM");
        timeSlot.setStartTime(LocalTime.of(10, 0));
        timeSlot.setEndTime(LocalTime.of(12, 0));
        timeSlotRepository.save(timeSlot);

        Location location = new Location();
        location.setBuildingName("VERILL");
        location.setRoomName("44");
        locationRepository.save(location);

        Faculty faculty = new Faculty();
        faculty.setTitle("MSCS");
        facultyRepository.save(faculty);

        Course ea = new Course();
        ea.setCourseNumber("CS544");
        ea.setDescription("Designing of Enterprise Architecture software for big corporations");
        ea.setName("Enterprise Architecture");

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

        Administrator administrator = new Administrator();
        HashSet<Role> role = createRole("ADMIN");
        administrator.setRole(role);
        administrator.setUserName("admin");
        administrator.setPassword(bCryptPasswordEncoder.encode("password"));
        administrator.setFirstName("Admin");
        administrator.setLastName("Lastname");
        adminRepository.save(administrator);

        administrator.setRole(role);
        Student student = new Student();
        student.setFirstName("Prabhat");
        student.setLastName("Gyawali");
        student.setStudentId("611941");
        student.setBarcode(611941l);
        student.setUserName("student");
        student.setRole(createRole("STUDENT"));
        student.setPassword(bCryptPasswordEncoder.encode("password"));
        studentRepository.save(student);

        CourseRegistration registration = new CourseRegistration();
        registration.setRegistrationDateTime(LocalDateTime.now());
        registration.setCourseOffering(eaOffering);
        registration.setStudent(student);
        courseRegistrationRepository.save(registration);

        student = new Student();
        student.setFirstName("Hoang");
        student.setLastName("Trinh");
        student.setStudentId("612339");
        student.setBarcode(612339l);
        student.setUserName("student1");
        student.setRole(createRole("STUDENT"));
        student.setPassword(bCryptPasswordEncoder.encode("password"));
        studentRepository.save(student);

        registration = new CourseRegistration();
        registration.setRegistrationDateTime(LocalDateTime.now());
        registration.setCourseOffering(eaOffering);
        registration.setStudent(student);
        courseRegistrationRepository.save(registration);

    }

    @NotNull
    private HashSet<Role> createRole(String...roles) {
        HashSet<Role> roleMap = new HashSet<>();
        for (String role : roles){
            roleMap.add(new Role(role));
        }
        return roleMap;
    }
}
