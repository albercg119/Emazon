package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.domain.utilities.exception.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.Emazon.Stock.configuration.Constants.*;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(VALIDATION_ERROR_FORMAT, errors),
                BAD_REQUEST,
                LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExists(CategoryAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(MESSAGE_FIELD, ex.getMessage());
        response.put(STATUS_FIELD, CONFLICT);
        response.put(TIMESTAMP_FIELD, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                ex.getMessage(),
                NOT_FOUND,
                LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ex.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                UNEXPECTED_ERROR,
                INTERNAL_SERVER_ERROR,
                LocalDateTime.now()));
    }
}