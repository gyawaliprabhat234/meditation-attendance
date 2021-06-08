package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}/search")
    public StudentDTO findStudentById(@PathVariable("id") String studentId) throws ResourceNotFoundException {
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/session")
    public List<AttendanceDTO> findAllStudentByLocation(@RequestParam("bldgName")String bldgName, @RequestParam("roomName") String roomName) throws ResourceNotFoundException {
        LocationDTO location = new LocationDTO(bldgName, roomName);
        return studentService.findAllStudentByLocation(location);
    }

}
