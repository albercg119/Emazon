package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.configuration.Constants;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
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
                Constants.VALIDATION_ERROR_MESSAGE + ": " + errors.toString(),
                Constants.STATUS_BAD_REQUEST,
                LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExists(CategoryAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.RESPONSE_MESSAGE_KEY, Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        response.put(Constants.RESPONSE_STATUS_KEY, Constants.STATUS_CONFLICT);
        response.put(Constants.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BrandAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExists(BrandAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.RESPONSE_MESSAGE_KEY, Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        response.put(Constants.RESPONSE_STATUS_KEY, Constants.STATUS_CONFLICT);
        response.put(Constants.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ArticleAlreadyExistsDomainException.class)
    public ResponseEntity<Map<String, String>> handleArticleAlreadyExists(ArticleAlreadyExistsDomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.RESPONSE_MESSAGE_KEY, Constants.ARTICLE_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        response.put(Constants.RESPONSE_STATUS_KEY, Constants.STATUS_CONFLICT);
        response.put(Constants.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,
                Constants.STATUS_NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE,
                Constants.STATUS_NOT_FOUND,
                LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ex.getMessage(),
                Constants.STATUS_BAD_REQUEST,
                LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                ex.getMessage(),
                Constants.STATUS_INTERNAL_SERVER_ERROR,
                LocalDateTime.now()));
    }
}