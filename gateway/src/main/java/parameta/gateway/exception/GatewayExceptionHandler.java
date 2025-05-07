package parameta.gateway.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GatewayExceptionHandler {
    @ExceptionHandler(EmployeeIsNotOfLegalAgeException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeIsNotOfLegalAgeException(EmployeeIsNotOfLegalAgeException exception) {
        ErrorDetails errorDetails = new ErrorDetails(
                EmployeeIsNotOfLegalAgeException.TYPE,
                EmployeeIsNotOfLegalAgeException.TITLE,
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now().toString(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDetails errorDetails = new ErrorDetails(
                URI.create("https://parameta.gateway.com/docs/errors/validation-error"),
                "Validation Error",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().toString(),
                errors.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> handleInvalidFormat(HttpMessageNotReadableException ex) {
        String message;
        if (ex.getCause().getCause() == null) {
            if (ex.getCause() instanceof InvalidFormatException) {
                message = "Invalid value for enum DocumentType. Please provide a valid value.";
            }else {
                message = "Validate fields that are not null.";
            }
        } else {
            message = "Invalid input format. Please check your JSON (e.g., dates must use format yyyy-MM-dd).";
        }

        ErrorDetails errorDetails = new ErrorDetails(
                URI.create("https://example.com/errors/invalid-format"),
                "Invalid Format",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().toString(),
                message
        );

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(EmployeeServiceException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeServiceException(EmployeeServiceException exception) {
        ErrorDetails errorDetails = new ErrorDetails(
                URI.create("https://parameta.gateway.com/docs/errors/employee-service-error"),
                "Employee Service Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().toString(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }
}
