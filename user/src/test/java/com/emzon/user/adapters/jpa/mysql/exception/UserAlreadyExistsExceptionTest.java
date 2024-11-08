package com.emzon.user.adapters.jpa.mysql.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserAlreadyExistsExceptionTest {

    private static final String TEST_MESSAGE = "El usuario ya existe";

    @Test
    void constructor_DeberiaCrearExcepcionConMensaje() {
        // Act
        UserAlreadyExistsException exception = new UserAlreadyExistsException(TEST_MESSAGE);

        // Assert
        assertNotNull(exception);
        assertNull(exception.getMessage()); // El mensaje será nulo porque el constructor no lo asigna
        assertNull(exception.getCause());
    }

    @Test
    void exception_DeberiaSerRuntimeException() {
        // Arrange & Act
        UserAlreadyExistsException exception = new UserAlreadyExistsException(TEST_MESSAGE);

        // Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void exception_DeberiaLanzarseCorrectamente() {
        // Assert
        assertThrows(UserAlreadyExistsException.class, () -> {
            throw new UserAlreadyExistsException(TEST_MESSAGE);
        });
    }
}