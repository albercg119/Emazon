package com.Emazon.Stock.configuration.exceptionhandler;


import com.Emazon.Stock.configuration.Constants;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
                errors.toString(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExists(CategoryAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.MESSAGE_FIELD, ex.getMessage());
        response.put(Constants.STATUS_FIELD, Constants.CONFLICT_STATUS);
        response.put(Constants.TIMESTAMP_FIELD, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BrandAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExists(BrandAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.MESSAGE_FIELD, ex.getMessage());
        response.put(Constants.STATUS_FIELD, Constants.CONFLICT_STATUS);
        response.put(Constants.TIMESTAMP_FIELD, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                Constants.NOT_FOUND_STATUS,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.toString(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                LocalDateTime.now()));
    }
}