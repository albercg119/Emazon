package com.emzon.user.configuration.exceptionhandler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionResponseTest {

    private static final String TEST_MESSAGE = "Test error message";
    private static final int TEST_STATUS_CODE = 400;
    private static final String TEST_DESCRIPTION = "Test description";

    @Test
    void constructor_ShouldInitializeAllFields() {
        // Act
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);

        // Assert
        assertEquals(TEST_MESSAGE, response.getMessage());
        assertEquals(TEST_STATUS_CODE, response.getStatusCode());
        assertEquals(TEST_DESCRIPTION, response.getDescription());
    }

    @Test
    void setMessage_ShouldUpdateMessage() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);
        String newMessage = "Updated message";

        // Act
        response.setMessage(newMessage);

        // Assert
        assertEquals(newMessage, response.getMessage());
    }

    @Test
    void setStatusCode_ShouldUpdateStatusCode() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);
        int newStatusCode = 500;

        // Act
        response.setStatusCode(newStatusCode);

        // Assert
        assertEquals(newStatusCode, response.getStatusCode());
    }

    @Test
    void setDescription_ShouldUpdateDescription() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);
        String newDescription = "Updated description";

        // Act
        response.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, response.getDescription());
    }

    @Test
    void getMessage_ShouldReturnMessage() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);

        // Act & Assert
        assertEquals(TEST_MESSAGE, response.getMessage());
    }

    @Test
    void getStatusCode_ShouldReturnStatusCode() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);

        // Act & Assert
        assertEquals(TEST_STATUS_CODE, response.getStatusCode());
    }

    @Test
    void getDescription_ShouldReturnDescription() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);

        // Act & Assert
        assertEquals(TEST_DESCRIPTION, response.getDescription());
    }

    @Test
    void constructor_ShouldHandleNullValues() {
        // Act
        ExceptionResponse response = new ExceptionResponse(null, TEST_STATUS_CODE, null);

        // Assert
        assertNull(response.getMessage());
        assertEquals(TEST_STATUS_CODE, response.getStatusCode());
        assertNull(response.getDescription());
    }

    @Test
    void setters_ShouldHandleNullValues() {
        // Arrange
        ExceptionResponse response = new ExceptionResponse(TEST_MESSAGE, TEST_STATUS_CODE, TEST_DESCRIPTION);

        // Act
        response.setMessage(null);
        response.setDescription(null);

        // Assert
        assertNull(response.getMessage());
        assertNull(response.getDescription());
    }
}