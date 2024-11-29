package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;  // Asegúrate de importar Map

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
        // Mock del BindingResult para simular errores de validación
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        // Simulamos la excepción MethodArgumentNotValidException
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        // Ejecutar el método del controlador
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleValidationExceptions(exception);

        // Validar que se devuelve el estado 400 y el mensaje correcto
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertEquals("{fieldName=defaultMessage}", response.getBody().getMessage());
    }

    @Test
    void handleCategoryAlreadyExistsDomainException_shouldReturnConflict() {
        // Simular la excepción con un mensaje personalizado
        String message = "Category already exists";
        CategoryAlreadyExistsDomainException exception = new CategoryAlreadyExistsDomainException(message);

        // Ejecutar el método del controlador
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleCategoryAlreadyExists(exception);

        // Validar que se devuelve el estado 409 y el mensaje correcto
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("409 CONFLICT", response.getBody().get("status"));
        assertEquals(message, response.getBody().get("message"));
    }

    @Test
    void handleNoDataFoundException_shouldReturnNotFound() {
        // Simular la excepción sin mensaje personalizado
        NoDataFoundException exception = new NoDataFoundException();

        // Ejecutar el método del controlador
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleNoDataFoundException(exception);

        // Validar que se devuelve el estado 404 y el mensaje por defecto
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.toString(), response.getBody().getStatus());
        assertEquals("No data found", response.getBody().getMessage());
    }

    @Test
    void handleElementNotFoundException_shouldReturnNotFound() {
        // Simular la excepción sin mensaje personalizado
        ElementNotFoundException exception = new ElementNotFoundException();

        // Ejecutar el método del controlador
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleElementNotFoundException(exception);

        // Validar que se devuelve el estado 404 y el mensaje por defecto
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.toString(), response.getBody().getStatus());
        assertEquals("Element not found", response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgumentException_shouldReturnBadRequest() {
        // Simular la excepción
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument");

        // Ejecutar el método del controlador
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleIllegalArgumentException(exception);

        // Validar que se devuelve el estado 400 y el mensaje correcto
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertEquals("Illegal argument", response.getBody().getMessage());
    }

    @Test
    void handleGeneralException_shouldReturnInternalServerError() {
        // Simular una excepción general
        Exception exception = new Exception("Internal server error");

        // Ejecutar el método del controlador
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleGeneralException(exception);

        // Validar que se devuelve el estado 500 y el mensaje correcto
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), response.getBody().getStatus());
        assertEquals("Internal server error", response.getBody().getMessage());
    }
}