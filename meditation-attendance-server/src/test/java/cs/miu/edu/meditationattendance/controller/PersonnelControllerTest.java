package cs.miu.edu.meditationattendance.controller;

import cs.miu.edu.meditationattendance.config.SecurityConfig;
import cs.miu.edu.meditationattendance.dto.StudentDTO;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.security.CustomUserDetailsService;
import cs.miu.edu.meditationattendance.security.JwtAuthenticationEntryPoint;
import cs.miu.edu.meditationattendance.security.JwtAuthenticationFilter;
import cs.miu.edu.meditationattendance.security.JwtTokenProvider;
import cs.miu.edu.meditationattendance.service.AttendanceService;
import cs.miu.edu.meditationattendance.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = PersonnelController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonnelControllerTest {
    @MockBean
    private StudentService studentService;
    @MockBean
    private AttendanceService attendanceService;
    @Autowired
    private MockMvc mockMvc;
    private StudentDTO studentDTO;

    @BeforeEach
    public void setup() {
        studentDTO = new StudentDTO();
        studentDTO.setStudentId("000-61-1941");
        studentDTO.setFirstName("Prabhat");
        studentDTO.setLastName("Gyawali");
        studentDTO.setCurrentCourse("CS544");
        studentDTO.setEmailAddress("pgyawali@miu.edu");
        studentDTO.setBarcode(611941l);
    }

    @Test
    public void it_should_return_the_student_when_we_pass_student_id() throws Exception {
        String studentId = "611941";
        when(studentService.findStudentById(studentId)).thenReturn(studentDTO);
        mockMvc.perform(get("/personnel/students/"+studentId))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId", Matchers.is("000-61-1941")))
                .andExpect(jsonPath("$.firstName", Matchers.is("Prabhat")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Gyawali")))
                .andExpect(jsonPath("$.currentCourse", Matchers.is("CS544")))
                .andExpect(jsonPath("$.emailAddress", Matchers.is("pgyawali@miu.edu")))
                .andExpect(jsonPath("$.barcode", Matchers.is(611941)));

    }

}