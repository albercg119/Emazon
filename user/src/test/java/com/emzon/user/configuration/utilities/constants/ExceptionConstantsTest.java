package com.emzon.user.configuration.utilities.constants;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionConstantsTest {

    @Test
    void constants_ShouldHaveCorrectValues() {
        // HTTP Status codes
        assertEquals(400, ExceptionConstants.HTTP_STATUS_BAD_REQUEST);
        assertEquals(500, ExceptionConstants.HTTP_STATUS_INTERNAL_SERVER_ERROR);

        // Error messages
        assertEquals("An unexpected error occurred", ExceptionConstants.UNEXPECTED_ERROR_MESSAGE);
        assertEquals("Invalid argument provided", ExceptionConstants.ILLEGAL_ARGUMENT_MESSAGE);
        assertEquals("Required parameter cannot be null", ExceptionConstants.NULL_PARAMETER_MESSAGE);
        assertEquals("Invalid format provided", ExceptionConstants.INVALID_FORMAT_MESSAGE);

        // Prefixes
        assertEquals("uri=", ExceptionConstants.URI_PREFIX);
        assertEquals("error=", ExceptionConstants.ERROR_PREFIX);
    }

    @Test
    void constructor_ShouldBePrivate() throws Exception {
        // Arrange
        Constructor<ExceptionConstants> constructor = ExceptionConstants.class.getDeclaredConstructor();

        // Assert
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        // Act & Assert - Verify constructor can be called through reflection
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}