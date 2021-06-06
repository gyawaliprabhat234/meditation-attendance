package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.dto.AttendanceDto;
import cs.miu.edu.meditationattendance.dto.CourseDto;
import cs.miu.edu.meditationattendance.repository.AttendanceRepository;
import cs.miu.edu.meditationattendance.repository.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseOfferingRepository courseRegistrationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<CourseDto> getCourses(Long studentId) {

        return courseRegistrationRepository.findByStudentId(studentId).stream().map(entity->new CourseDto(entity.getName())).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAttendance(Long studentId) {
        return attendanceRepository.findAttendanceByStudentId(studentId).stream().map(entity-> new AttendanceDto(entity.getStudent().getStudentId())).collect(Collectors.toList());
    }
}