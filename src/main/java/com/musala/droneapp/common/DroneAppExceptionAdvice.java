package com.musala.droneapp.common;

import com.musala.droneapp.exceptions.BatteryCriticallyLowException;
import com.musala.droneapp.exceptions.RecordExistException;
import com.musala.droneapp.exceptions.RecordNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class DroneAppExceptionAdvice {

    @ExceptionHandler(RecordExistException.class)
    public ResponseEntity<String> handleRecordExistsException(RecordExistException ex) {
        log.warn("Error::RecordExists {}", ex);
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex) {
        log.warn("Error::RecordNotFound {}", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BatteryCriticallyLowException.class)
    public ResponseEntity<String> handleBatteryLowException(BatteryCriticallyLowException ex) {
        log.warn("Error::BatteryCriticallyLowException {}", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.warn("Error::RuntimeException {}", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String>  onConstraintValidationException(ConstraintViolationException ex) {
        StringBuilder errors = new StringBuilder();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errors.append(violation.getMessage()).append("\\s");
        }
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String>  onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.append(fieldError.getDefaultMessage()).append("\\s");
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
