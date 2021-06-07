package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonnelController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{id}/search")
    public StudentDTO findStudentById(@PathVariable("id") String studentId) throws ResourceNotFoundException {
        return studentService.findStudentById(studentId);
    }
}
