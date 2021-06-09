package cs.miu.edu.meditationattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {
    private HttpStatus httpStatus;
    private String message;
}
