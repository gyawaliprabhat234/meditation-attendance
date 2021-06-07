package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;

import java.util.List;

public interface StudentService {
    public StudentDTO findStudentById(String studentId) throws ResourceNotFoundException;

    List<AttendanceDTO> findAllStudentByLocation(LocationDTO location) throws ResourceNotFoundException;
}
