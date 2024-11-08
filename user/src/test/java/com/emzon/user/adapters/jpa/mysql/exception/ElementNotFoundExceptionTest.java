package com.emzon.user.adapters.jpa.mysql.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElementNotFoundExceptionTest {

    @Test
    void constructor_DeberiaCrearExcepcionSinMensaje() {
        // Act
        ElementNotFoundException exception = new ElementNotFoundException();

        // Assert
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void exception_DeberiaSerRuntimeException() {
        // Act
        ElementNotFoundException exception = new ElementNotFoundException();

        // Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void exception_DeberiaPoderSerCapturadaComoRuntimeException() {
        // Arrange
        RuntimeException capturedException = null;

        // Act
        try {
            throw new ElementNotFoundException();
        } catch (RuntimeException e) {
            capturedException = e;
        }

        // Assert
        assertNotNull(capturedException);
        assertTrue(capturedException instanceof ElementNotFoundException);
    }

    @Test
    void exception_DeberiaLanzarseCorrectamente() {
        // Act & Assert
        assertThrows(ElementNotFoundException.class, () -> {
            throw new ElementNotFoundException();
        });
    }

    @Test
    void stackTrace_DeberiaSerAccesible() {
        // Arrange
        ElementNotFoundException exception = new ElementNotFoundException();

        // Act & Assert
        assertNotNull(exception.getStackTrace());
        assertTrue(exception.getStackTrace().length > 0);
    }
}