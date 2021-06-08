package cs.miu.edu.meditationattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
