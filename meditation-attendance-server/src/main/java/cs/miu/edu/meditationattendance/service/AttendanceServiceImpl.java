package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.Attendance;
import cs.miu.edu.meditationattendance.domain.ClassSession;
import cs.miu.edu.meditationattendance.domain.CourseOffering;
import cs.miu.edu.meditationattendance.domain.Student;
import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.AttendanceRepository;
import cs.miu.edu.meditationattendance.repository.ClassSessionRepository;
import cs.miu.edu.meditationattendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private ClassSessionRepository classSessionRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Transactional
    public List<AttendanceDTO> findAllAttendanceByStudentId(String id) throws ResourceNotFoundException {
        List<Attendance> attendanceList = attendanceRepo.findAllAttendanceByStudentId(id);
        if(attendanceList.size() == 0)
            throw new ResourceNotFoundException("Records not found with id("+id+")");
        return attendanceList.stream().map(x -> mapToAttendanceDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public AttendanceDTO saveAttendance(AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        Attendance attendance = this.mapToAttendance(attendanceDTO);
        Attendance newAttendance = attendanceRepo.save(attendance);
        return mapToAttendanceDTO(newAttendance);
    }

    @Transactional
    public boolean deleteAttendance(Long id) throws ResourceNotFoundException {
        Optional<Attendance> attendance = attendanceRepo.findById(id);
        if (!attendance.isPresent())
            throw new ResourceNotFoundException("Attendance Not Found");
        attendanceRepo.delete(attendance.get());
        return true;
    }

    @Override
    @Transactional
    public boolean saveAllAttendance(List<AttendanceDTO> attendanceDTOS) throws ResourceNotFoundException {
        for(AttendanceDTO attendanceDTO: attendanceDTOS){
            this.saveAttendance(attendanceDTO);
        }
        return true;
    }

    @Transactional
    public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        Optional<Attendance> attendance = attendanceRepo.findById(attendanceDTO.getId());
        if(!attendance.isPresent())
            throw new ResourceNotFoundException("Attendance not found with id("+ attendanceDTO.getId()+")");
        attendance.get().setTimeStamp(attendanceDTO.getBarCodeScanTime());

        //If class Session is not changed then return the attendence to user
        if (attendance.get().getClassSession().getSessionId().equals(attendanceDTO.getSessionId())) {
            return this.mapToAttendanceDTO(attendanceRepo.save(attendance.get()));
        }
        //Doing more work to check the class Session exist in the database and setting the value;
        Optional<ClassSession> classSession = classSessionRepo.findById(attendanceDTO.getSessionId());
        if(!classSession.isPresent())
            throw new ResourceNotFoundException("Class Session id"+(attendanceDTO.getSessionId())+" is not found");
        attendance.get().setClassSession(classSession.get());
        return this.mapToAttendanceDTO(attendanceRepo.save(attendance.get()));
    }

    private Attendance mapToAttendance(AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        Attendance attendance = new Attendance();
        //attendance.setBarCode(attendanceDTO.getBarCode());
        Optional<Student> student = studentRepo.findByBarcode(attendanceDTO.getBarCode());
        if(!student.isPresent())
            throw new ResourceNotFoundException("Student not found in this record");
        Optional<ClassSession> classSession = classSessionRepo.findById(attendanceDTO.getSessionId());
        if(!classSession.isPresent())
            throw new ResourceNotFoundException("Class session is not found");
        attendance.setClassSession(classSession.get());
        attendance.setTimeStamp(attendanceDTO.getBarCodeScanTime());
        attendance.setStudent(student.get());
        return attendance;
    }

    private AttendanceDTO mapToAttendanceDTO(Attendance attendance) {
        Student student = attendance.getStudent();
        CourseOffering courseOffering = attendance.getClassSession().getCourseOffering();
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setSessionId(attendance.getClassSession().getSessionId());
        attendanceDTO.setStudentId(student.getStudentId());
        attendanceDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
        attendanceDTO.setBarCode(student.getBarcode());
        attendanceDTO.setBarCodeScanTime(attendance.getTimeStamp());
        attendanceDTO.setTimeSlotCode(attendance.getClassSession().getTimeslot().getCode());
        attendanceDTO.setCourseEndDate(courseOffering.getEndDate());
        attendanceDTO.setCourseStartDate(courseOffering.getStartDate());
        attendanceDTO.setCourseName(courseOffering.getCourse().getName());
        return attendanceDTO;
    }
}
