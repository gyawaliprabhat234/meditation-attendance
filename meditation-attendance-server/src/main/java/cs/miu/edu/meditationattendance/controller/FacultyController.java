package cs.miu.edu.meditationattendance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @GetMapping("/")
    @PreAuthorize("hasAuthority('FACULTY')")
    public String faculty() {
        return "Welcome to home page1111!!";
    }

    @GetMapping("/basic")
    public String basic() {
        return "Welcome to home page1111!!";
    }
}
