package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.domain.utilities.exception.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.usecase.CategoryUseCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private CategoryUseCase categoryUseCase;

    @InjectMocks
    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {

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
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertEquals("{fieldName=defaultMessage}", response.getBody().getMessage());
    }

    @Test
    void handleNoDataFoundException_shouldReturnNotFound() {

        NoDataFoundException exception = new NoDataFoundException();


        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleNoDataFoundException(exception);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.toString(), response.getBody().getStatus());
        assertEquals("No data found", response.getBody().getMessage());
    }

    @Test
    void handleElementNotFoundException_shouldReturnNotFound() {

        ElementNotFoundException exception = new ElementNotFoundException();


        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleElementNotFoundException(exception);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.toString(), response.getBody().getStatus());
        assertEquals("Element not found", response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgumentException_shouldReturnBadRequest() {

        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument");


        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleIllegalArgumentException(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertEquals("Illegal argument", response.getBody().getMessage());
    }

    @Test
    void handleGeneralException_shouldReturnInternalServerError() {

        Exception exception = new Exception("Internal server error");


        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleGeneralException(exception);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), response.getBody().getStatus());
        assertEquals("Internal server error", response.getBody().getMessage());
    }
}