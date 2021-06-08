package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AttendanceService {
    public List<AttendanceDTO> findAllAttendanceByStudentId(String id) throws ResourceNotFoundException;
    public AttendanceDTO saveAttendance(AttendanceDTO attendanceDTO) throws ResourceNotFoundException;
    public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO) throws ResourceNotFoundException;
    public boolean deleteAttendance(Long id) throws ResourceNotFoundException;
    boolean saveAllAttendance(List<AttendanceDTO> attendanceDTOS) throws ResourceNotFoundException;
}
