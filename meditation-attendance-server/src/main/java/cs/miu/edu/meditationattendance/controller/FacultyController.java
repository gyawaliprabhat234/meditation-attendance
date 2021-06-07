package cs.miu.edu.meditationattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.miu.edu.meditationattendance.dto.ClassDto;
import cs.miu.edu.meditationattendance.dto.CourseDto;
import cs.miu.edu.meditationattendance.service.CourseService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('FACULTY')")
    public String faculty() {
        return "Welcome to home page1111!!";
    }

    @GetMapping("/basic")
    public String basic() {
        return "Welcome to home page1111!!";
    }

    @GetMapping("/courses")
    public List<CourseDto> getCoursesPastSixMoths() throws Exception{
    	return courseService.coursesPastSixMonths();
    }

    @GetMapping("/courses/attendance")
    public List<ClassDto> coursesWithAttendance() throws Exception{
    	return courseService.coursesWithAttendance();
    }
}
