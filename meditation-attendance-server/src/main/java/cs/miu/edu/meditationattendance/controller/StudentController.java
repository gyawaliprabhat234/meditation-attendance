package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.dto.LocationDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.UserRepository;
import cs.miu.edu.meditationattendance.security.CurrentUser;
import cs.miu.edu.meditationattendance.security.UserPrincipal;
import cs.miu.edu.meditationattendance.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api( tags = "Student")
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
    @ApiOperation(value = "This method is used to get the attendance of a student in a pre-defined course")
    @GetMapping("/attendance/{courseNumber}")
    public List<AttendanceDTO> findAllAttendanceByCourseNumber(@CurrentUser UserPrincipal userPrincipal, @PathVariable("courseNumber") String courseNumber) throws ResourceNotFoundException {

        String studentId = studentService.findStudentObjById(userPrincipal.getId()).getStudentId();
        return studentService.findAllAttendanceByCourseNumber(courseNumber, studentId);
    }
    @ApiOperation(value = "This method is used to get all the courses in which a student is registered in")
    @GetMapping("/courses")
    public List<CourseDTO> findAllCoursesByStudentId(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        String studentId = studentService.findStudentObjById(userPrincipal.getId()).getStudentId();
        return studentService.findAllCoursesByStudentId(studentId);
    }

}
