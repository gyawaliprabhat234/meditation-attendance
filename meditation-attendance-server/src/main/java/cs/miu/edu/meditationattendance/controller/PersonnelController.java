package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.dto.Response;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ApiException;
import cs.miu.edu.meditationattendance.exception.NotHandledException;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import cs.miu.edu.meditationattendance.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('PERSONNEL') OR hasAuthority('ADMIN')")
    @GetMapping("/students/{id}")
    public StudentDTO findStudentById(@PathVariable("id") String studentId) throws NotHandledException {
        try {
            return studentService.findStudentById(studentId);
        } catch (Exception ex) {
            throw new NotHandledException(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('PERSONNEL') OR hasAuthority('ADMIN')")
    @GetMapping("/students/{id}/attendances")
    public List<AttendanceDTO> findAllAttendanceByStudentId(@PathVariable String id) throws NotHandledException {
        try {
            return attendanceService.findAllAttendanceByStudentId(id);
        } catch (Exception ex) {
            throw new NotHandledException(ex.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('PERSONNEL') OR hasAuthority('ADMIN')")
    @PostMapping("/save")
    public AttendanceDTO saveAttendance(@RequestBody AttendanceDTO attendanceDTO) throws NotHandledException {
        try {
            return attendanceService.saveAttendance(attendanceDTO);
        } catch (Exception ex) {
            throw new NotHandledException(ex.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('PERSONNEL') OR hasAuthority('ADMIN')")
    @PostMapping("/attendances/{attendanceId}/update")
    public AttendanceDTO updateAttendance(@PathVariable Long attendanceId, @RequestBody AttendanceDTO attendanceDTO) throws NotHandledException {

        try {
            attendanceDTO.setId(attendanceId);
            return attendanceService.updateAttendance(attendanceDTO);
        } catch (Exception ex) {
            throw new NotHandledException(ex.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('PERSONNEL') OR hasAuthority('ADMIN')")
    @DeleteMapping("/attendances/{id}/delete")
    public ResponseEntity<Response> deleteAttendance(@PathVariable Long id) throws ResourceNotFoundException, NotHandledException {
        try {
            if (attendanceService.deleteAttendance(id)) {
                return new ResponseEntity<>(new Response(HttpStatus.OK, "Deleted Successfully"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, "Can't Deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            throw new NotHandledException(ex.getMessage());
        }

    }

}
