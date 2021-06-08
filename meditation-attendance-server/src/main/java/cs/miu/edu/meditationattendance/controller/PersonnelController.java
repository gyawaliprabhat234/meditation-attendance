package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import cs.miu.edu.meditationattendance.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel")
@AllArgsConstructor
public class PersonnelController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/students/{id}")
    public StudentDTO findStudentById(@PathVariable("id") String studentId) throws ResourceNotFoundException {
        return studentService.findStudentById(studentId);
    }


    //   @PreAuthorize("hasAuthority('PERe')")
    @GetMapping("/{id}/all")
    public List<AttendanceDTO> findAllAttendanceByStudentId(@PathVariable String id) throws ResourceNotFoundException {
        return attendanceService.findAllAttendanceByStudentId(id);
    }

    //    @PreAuthorize("hasAuthority('FACULTY')")
    @PostMapping("/save")
    public AttendanceDTO saveAttendance(@RequestBody AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        return attendanceService.saveAttendance(attendanceDTO);
    }

    @PostMapping("/{attendanceId}/update")
    public AttendanceDTO updateAttendance(@PathVariable Long attendanceId, @RequestBody AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        attendanceDTO.setId(attendanceId);
        return attendanceService.updateAttendance(attendanceDTO);
    }

    @DeleteMapping("/{id}/delete")
    public boolean deleteAttendance(@PathVariable Long id) throws ResourceNotFoundException {
        return attendanceService.deleteAttendance(id);
    }

    @PostMapping("/saveAll")
    public boolean saveAllAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS) throws ResourceNotFoundException {
        return attendanceService.saveAllAttendance(attendanceDTOS);
    }
}
