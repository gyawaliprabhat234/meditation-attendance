package cs.miu.edu.meditationattendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private String studentId;
    private String id;
    private String studentName;
    private String currentCourse;
    private Long barcode;
    private String status;
    private LocalDateTime entryDateTime;
    private String emailAddress;
}
