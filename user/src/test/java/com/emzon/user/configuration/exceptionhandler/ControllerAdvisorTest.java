package com.emzon.user.configuration.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static com.emzon.user.configuration.utilities.constants.ExceptionConstants.*;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    @InjectMocks
    private ControllerAdvisor controllerAdvisor;

    @Mock
    private WebRequest webRequest;

    private static final String TEST_DESCRIPTION = "test-uri=/api/test";
    private static final String CUSTOM_ERROR_MESSAGE = "Custom error message";

    @BeforeEach
    void setUp() {
        when(webRequest.getDescription(false)).thenReturn(TEST_DESCRIPTION);
    }

    @Test
    void handleIllegalArgumentException_WithCustomMessage_ShouldReturnCustomMessage() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException(CUSTOM_ERROR_MESSAGE);

        // Act
        ResponseEntity<ExceptionResponse> responseEntity =
                controllerAdvisor.handleIllegalArgumentException(exception, webRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HTTP_STATUS_BAD_REQUEST, responseEntity.getBody().getStatusCode());
        assertEquals(CUSTOM_ERROR_MESSAGE, responseEntity.getBody().getMessage());
        assertEquals(TEST_DESCRIPTION, responseEntity.getBody().getDescription());
    }

    @Test
    void handleIllegalArgumentException_WithNullMessage_ShouldReturnDefaultMessage() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException();

        // Act
        ResponseEntity<ExceptionResponse> responseEntity =
                controllerAdvisor.handleIllegalArgumentException(exception, webRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HTTP_STATUS_BAD_REQUEST, responseEntity.getBody().getStatusCode());
        assertEquals(ILLEGAL_ARGUMENT_MESSAGE, responseEntity.getBody().getMessage());
        assertEquals(TEST_DESCRIPTION, responseEntity.getBody().getDescription());
    }

    @Test
    void handleAllUncaughtException_ShouldReturnInternalServerError() {
        // Arrange
        Exception exception = new RuntimeException("Some unexpected error");

        // Act
        ResponseEntity<ExceptionResponse> responseEntity =
                controllerAdvisor.handleAllUncaughtException(exception, webRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HTTP_STATUS_INTERNAL_SERVER_ERROR, responseEntity.getBody().getStatusCode());
        assertEquals(UNEXPECTED_ERROR_MESSAGE, responseEntity.getBody().getMessage());
        assertEquals(TEST_DESCRIPTION, responseEntity.getBody().getDescription());
    }
}