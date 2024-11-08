package com.emzon.user.adapters.jpa.mysql.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoDataFoundExceptionTest {

    @Test
    void constructor_DeberiaCrearExcepcionSinMensaje() {
        // Act
        NoDataFoundException exception = new NoDataFoundException();

        // Assert
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void exception_DeberiaSerRuntimeException() {
        // Arrange & Act
        NoDataFoundException exception = new NoDataFoundException();

        // Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void exception_DeberiaLanzarseCorrectamente() {
        // Assert
        assertThrows(NoDataFoundException.class, () -> {
            throw new NoDataFoundException();
        });
    }
}