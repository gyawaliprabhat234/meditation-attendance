package cs.miu.edu.meditationattendance.controller;


import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.dto.CourseDto;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public List<CourseDto> student(Long studentId) {
        return studentService.getCourses(studentId);
    }

}
