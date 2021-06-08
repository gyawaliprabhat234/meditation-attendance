package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.dto.AttendanceDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.security.CurrentUser;
import cs.miu.edu.meditationattendance.security.UserPrincipal;
import cs.miu.edu.meditationattendance.service.AdminService;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/assign")
    public boolean saveAttendance(@CurrentUser UserPrincipal currentUser, @RequestParam("userId") Long userId, String newRole) throws ResourceNotFoundException {
        return adminService.assignRole(userId, newRole);
    }
}
