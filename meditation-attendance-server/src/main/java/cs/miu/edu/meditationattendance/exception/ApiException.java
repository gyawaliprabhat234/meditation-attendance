package cs.miu.edu.meditationattendance.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timeStamp;
//    private Throwable cause;
}
