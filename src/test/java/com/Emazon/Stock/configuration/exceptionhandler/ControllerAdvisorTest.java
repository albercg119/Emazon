package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerAdvisorTest {

    @InjectMocks
    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new ControllerAdvisor();
    }

    @Test
    void handleValidationExceptions_shouldReturnBadRequest() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
        assertEquals("{fieldName=defaultMessage}", response.getBody().getMessage());
    }

    @Test
    void handleBrandAlreadyExists_shouldReturnConflict() {
        BrandAlreadyExistsDomainException exception = new BrandAlreadyExistsDomainException("Brand already exists");

        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleBrandAlreadyExists(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Brand already exists", response.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
        assertEquals(Constants.STATUS_CONFLICT, response.getBody().get(Constants.RESPONSE_STATUS_KEY));
    }

    @Test
    void handleArticleAlreadyExists_shouldReturnConflict() {
        ArticleAlreadyExistsDomainException exception = new ArticleAlreadyExistsDomainException("Article already exists");

        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleArticleAlreadyExists(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Article already exists", response.getBody().get("message"));
        assertEquals("409 CONFLICT", response.getBody().get("status"));
    }

    @Test
    void handleNoDataFoundException_shouldReturnNotFound() {
        NoDataFoundException exception = new NoDataFoundException("No data found");

        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleNoDataFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Constants.STATUS_NOT_FOUND, response.getBody().getStatus());
        assertEquals("No data found", response.getBody().getMessage());
    }

    @Test
    void handleElementNotFoundException_shouldReturnNotFound() {
        ElementNotFoundException exception = new ElementNotFoundException("Element not found");

        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleElementNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Constants.STATUS_NOT_FOUND, response.getBody().getStatus());
        assertEquals("Element not found", response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgumentException_shouldReturnBadRequest() {
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument");

        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
        assertEquals("Illegal argument", response.getBody().getMessage());
    }

    @Test
    void handleGeneralException_shouldReturnInternalServerError() {
        Exception exception = new Exception("Internal server error");

        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Constants.STATUS_INTERNAL_SERVER_ERROR, response.getBody().getStatus());
        assertEquals("Internal server error", response.getBody().getMessage());
    }
}