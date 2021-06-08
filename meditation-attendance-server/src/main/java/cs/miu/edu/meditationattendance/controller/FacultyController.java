package cs.miu.edu.meditationattendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.miu.edu.meditationattendance.dto.ClassDTO;
import cs.miu.edu.meditationattendance.dto.CourseDTO;
import cs.miu.edu.meditationattendance.security.CurrentUser;
import cs.miu.edu.meditationattendance.security.UserPrincipal;
import cs.miu.edu.meditationattendance.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api( tags = "Faculty")
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;
    
    @ApiOperation(value = "This method is used to get the courses of six months ago.")
    @PreAuthorize("hasAuthority('FACULTY')")
    @GetMapping("/courses")
    public List<CourseDTO> getCoursesPastSixMoths(@CurrentUser UserPrincipal currentUser) throws Exception{
    	Long userId = currentUser.getId();
    	return courseService.coursesPastSixMonths(userId);
    }
    
    @ApiOperation(value = "This method is used to get the attenfance per courses.")
    @PreAuthorize("hasAuthority('FACULTY')")
    @GetMapping("/courses/attendance")
    public List<ClassDTO> coursesWithAttendance(@CurrentUser UserPrincipal currentUser) throws Exception{
    	Long userId = currentUser.getId();
    	return courseService.coursesWithAttendance(userId);
    }
}
