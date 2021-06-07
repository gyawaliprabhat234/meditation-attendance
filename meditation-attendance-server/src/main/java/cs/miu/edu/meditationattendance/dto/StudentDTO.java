package cs.miu.edu.meditationattendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private String studentId;
    private String id;
    private String firstName;
    private String lastName;
    private String currentCourse;
    private Long barcode;
    private String status;
    private LocalDateTime entryDateTime;
    private String emailAddress;
    private String userName;
}
