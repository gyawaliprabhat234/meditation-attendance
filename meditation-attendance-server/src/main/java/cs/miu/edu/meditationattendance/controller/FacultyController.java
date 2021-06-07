package cs.miu.edu.meditationattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api( tags = "Faculty")
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
    
    @ApiOperation(value = "This method is used to get the courses of six months ago.")
    @GetMapping("/courses")
    public List<CourseDTO> getCoursesPastSixMoths() throws Exception{
    	return courseService.coursesPastSixMonths();
    }
    
    @ApiOperation(value = "This method is used to get the attenfance per courses.")
    @GetMapping("/courses/attendance")
    public List<ClassDTO> coursesWithAttendance() throws Exception{
    	return courseService.coursesWithAttendance();
    }
}
