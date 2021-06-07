package cs.miu.edu.meditationattendance.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
	
	private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
	public String studentId;
	private String status;
	private LocalDateTime entryDateTime;
	private Long barcode;

}
