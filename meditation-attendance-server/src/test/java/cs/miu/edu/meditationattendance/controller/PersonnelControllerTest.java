package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.config.SecurityConfig;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.security.CustomUserDetailsService;
import cs.miu.edu.meditationattendance.security.JwtAuthenticationEntryPoint;
import cs.miu.edu.meditationattendance.security.JwtTokenProvider;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonnelController.class)
@Import(SecurityConfig.class)
class PersonnelControllerTest {
    @MockBean
    private StudentService studentService;

    @MockBean
    private AttendanceService attendanceService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MockMvc mockMvc;

    private StudentDTO studentDTO;

    @BeforeEach
    public void setup() {
        studentDTO = new StudentDTO();
        studentDTO.setStudentId("611941");
        studentDTO.setFirstName("Prabhat");
        studentDTO.setLastName("Gyawali");
        studentDTO.setCurrentCourse("CS544");
        studentDTO.setEmailAddress("pgyawali@miu.edu");
        studentDTO.setBarcode(611941l);
    }

    @Test
    public void it_should_return_the_student_when_we_pass_student_id() throws Exception {
        String studentId = "611941";
        Mockito.when(studentService.findStudentById(studentId)).thenReturn(studentDTO);
        mockMvc.perform(get("/personnel/students/"+studentId))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId", Matchers.is("611941")))
                .andExpect(jsonPath("$.firstName", Matchers.is("Prabhat")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Gyawali")))
                .andExpect(jsonPath("$.currentCourse", Matchers.is("CS544")))
                .andExpect(jsonPath("$.emailAddress", Matchers.is("pgyawali@miu.edu")))
                .andExpect(jsonPath("$.barcode", Matchers.is(611941)));

    }

}