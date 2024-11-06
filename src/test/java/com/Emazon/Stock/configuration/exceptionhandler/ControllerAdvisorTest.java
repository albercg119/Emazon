package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.configuration.Constants;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.ElementNotFoundException;
import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ControllerAdvisorTest {

    private ControllerAdvisor controllerAdvisor;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        openMocks(this);
        controllerAdvisor = new ControllerAdvisor();
    }

    @Test
    void handleValidationExceptions() {
        // Arrange
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(
                new FieldError("object", "field1", "error1"),
                new FieldError("object", "field2", "error2")
        ));

        // Act
        ResponseEntity<ExceptionResponse> response =
                controllerAdvisor.handleValidationExceptions(methodArgumentNotValidException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
    }

    @Test
    void handleCategoryAlreadyExists() {
        // Arrange
        CategoryAlreadyExistsDomainException exception =
                new CategoryAlreadyExistsDomainException(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleCategoryAlreadyExists(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_CONFLICT, response.getBody().get(Constants.RESPONSE_STATUS_KEY));
        assertEquals(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                response.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

    @Test
    void handleBrandAlreadyExists() {
        // Arrange
        BrandAlreadyExistsDomainException exception =
                new BrandAlreadyExistsDomainException(Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleBrandAlreadyExists(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_CONFLICT, response.getBody().get(Constants.RESPONSE_STATUS_KEY));
        assertEquals(Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                response.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

    @Test
    void handleArticleAlreadyExists() {
        // Arrange
        ArticleAlreadyExistsDomainException exception =
                new ArticleAlreadyExistsDomainException(Constants.ARTICLE_ALREADY_EXISTS_EXCEPTION_MESSAGE);

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleArticleAlreadyExists(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("409 CONFLICT", response.getBody().get("status"));
        assertEquals(Constants.ARTICLE_ALREADY_EXISTS_EXCEPTION_MESSAGE, response.getBody().get("message"));
    }

    @Test
    void handleNoDataFoundException() {
        // Arrange
        NoDataFoundException exception = new NoDataFoundException();

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleNoDataFoundException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_NOT_FOUND, response.getBody().getStatus());
    }

    @Test
    void handleElementNotFoundException() {
        // Arrange
        ElementNotFoundException exception = new ElementNotFoundException();

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleElementNotFoundException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_NOT_FOUND, response.getBody().getStatus());
    }

    @Test
    void handleIllegalArgumentException() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Test message");

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleIllegalArgumentException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_BAD_REQUEST, response.getBody().getStatus());
        assertEquals("Test message", response.getBody().getMessage());
    }

    @Test
    void handleGeneralException() {
        // Arrange
        Exception exception = new Exception("Test message");

        // Act
        ResponseEntity<ExceptionResponse> response = controllerAdvisor.handleGeneralException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Constants.STATUS_INTERNAL_SERVER_ERROR, response.getBody().getStatus());
        assertEquals("Test message", response.getBody().getMessage());
    }
}