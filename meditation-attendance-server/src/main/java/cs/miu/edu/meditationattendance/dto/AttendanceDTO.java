package cs.miu.edu.meditationattendance.dto;

import cs.miu.edu.meditationattendance.domain.Course;
import cs.miu.edu.meditationattendance.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private Long id;
    private String studentId;
    private String studentName;
    private String firstName;
    private String lastName;
    private String courseName;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private String timeSlotCode;
    private String timeSlotCodeDescription;
    private Long sessionId;
    private LocalDateTime timeStamp;
    private String emailAddress;
    private String buildingName;
    private Long barCode;
}

