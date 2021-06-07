package cs.miu.edu.meditationattendance.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseStatus
public class ApiExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiException> resourceNotFoundException(ResourceNotFoundException exception) {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

//   @ExceptionHandler(value={NoHandlerFoundException.class})
//    public ResponseEntity<ApiException> handleNoHandlerFoundExceptionEx(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        Map<String,String> responseBody = new HashMap<>();
////        responseBody.put("path",request.getContextPath());
////        responseBody.put("message","The URL you have reached is not in service at this time (404).");
//        return new ResponseEntity<>(new ApiException(HttpStatus.BAD_REQUEST,"internal error", LocalDateTime.now()),HttpStatus.NOT_FOUND);
//    }
}
