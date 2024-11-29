package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.configuration.Constants;
import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerAdvisorTest {

    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new ControllerAdvisor();
    }


    @Test
    void handleValidationExceptions_ShouldReturnBadRequest() {
        // Arrange
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        FieldError fieldError = new FieldError("object", "field", "message");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleValidationExceptions(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
    }

    @Test
    void handleCategoryAlreadyExists_ShouldReturnConflict() {
        // Arrange
        String errorMessage = "Category already exists";
        CategoryAlreadyExistsDomainException ex = new CategoryAlreadyExistsDomainException(errorMessage);

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleCategoryAlreadyExists(ex);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
        assertEquals(Constants.STATUS_CONFLICT, response.getBody().get(Constants.RESPONSE_STATUS_KEY));
    }

    @Test
    void handleBrandAlreadyExists_ShouldReturnConflict() {
        // Arrange
        String errorMessage = "Brand already exists";
        BrandAlreadyExistsDomainException ex = new BrandAlreadyExistsDomainException(errorMessage);

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleBrandAlreadyExists(ex);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
        assertEquals(Constants.STATUS_CONFLICT, response.getBody().get(Constants.RESPONSE_STATUS_KEY));
    }

    @Test
    void handleNoDataFoundException_ShouldReturnNotFound() {
        // Arrange
        NoDataFoundException ex = new NoDataFoundException();

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleNoDataFoundException(ex);

        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El código de estado debe ser NOT_FOUND");

        ExceptionResponse errorResponse = response.getBody();
        assertNotNull(errorResponse, "El cuerpo de la respuesta no debe ser null");
        assertNotNull(errorResponse.getMessage(), "El mensaje no debe ser null");
        assertEquals(Constants.STATUS_NOT_FOUND, errorResponse.getStatus(), "El estado debe ser NOT_FOUND");

        // Validación del timestamp
        assertNotNull(errorResponse.getTimestamp(), "El timestamp no debe ser null");
        assertTrue(
                errorResponse.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)) &&
                        errorResponse.getTimestamp().isAfter(LocalDateTime.now().minusSeconds(1)),
                "El timestamp debe estar dentro del rango de un segundo"
        );
    }

    @Test
    void handleElementNotFoundException_ShouldReturnNotFound() {
        // Arrange
        ElementNotFoundException ex = new ElementNotFoundException();

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleElementNotFoundException(ex);

        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El código de estado debe ser NOT_FOUND");

        ExceptionResponse errorResponse = response.getBody();
        assertNotNull(errorResponse, "El cuerpo de la respuesta no debe ser null");
        assertNotNull(errorResponse.getMessage(), "El mensaje no debe ser null");
        assertEquals(Constants.STATUS_NOT_FOUND, errorResponse.getStatus(), "El estado debe ser NOT_FOUND");

        // Validación del timestamp
        assertNotNull(errorResponse.getTimestamp(), "El timestamp no debe ser null");
        assertTrue(
                errorResponse.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)) &&
                        errorResponse.getTimestamp().isAfter(LocalDateTime.now().minusSeconds(1)),
                "El timestamp debe estar dentro del rango de un segundo"
        );
    }


    @Test
    void handleIllegalArgumentException_ShouldReturnBadRequest() {
        // Arrange
        String errorMessage = "Invalid argument";
        IllegalArgumentException ex = new IllegalArgumentException(errorMessage);

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleIllegalArgumentException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
    }

    @Test
    void handleGeneralException_ShouldReturnInternalServerError() {
        // Arrange
        String errorMessage = "Unexpected error";
        Exception ex = new Exception(errorMessage);

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleGeneralException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(Constants.STATUS_INTERNAL_SERVER_ERROR, response.getBody().getStatus());
    }
}