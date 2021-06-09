package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/saveAll")
    public boolean saveAllAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS) throws ResourceNotFoundException {
        return attendanceService.saveAllAttendance(attendanceDTOS);
    }

    @GetMapping("/session")
    public List<AttendanceDTO> findAllStudentByLocation(@RequestParam("bldgName")String bldgName, @RequestParam("roomName") String roomName) throws ResourceNotFoundException {
        LocationDTO location = new LocationDTO(bldgName, roomName);
        return studentService.findAllStudentByLocation(location);
    }
}
