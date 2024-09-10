package com.Emazon.Stock.configuration.exceptionhandler;

import com.Emazon.Stock.adapters.jpa.mysql.exception.CategoryAlreadyExistsException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class ExceptionResponseTest {

    @Test
    void testExceptionResponse() {
        // Arrange: prepara los datos necesarios para el test
        String expectedMessage = "Error occurred";
        String expectedStatus = "500 INTERNAL_SERVER_ERROR";
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        // Act: crea una instancia de ExceptionResponse con los valores de prueba
        ExceptionResponse exceptionResponse = new ExceptionResponse(expectedMessage, expectedStatus, expectedTimestamp);

        // Assert: verifica que los valores obtenidos coinciden con los esperados
        assertThat(exceptionResponse.getMessage()).isEqualTo(expectedMessage);
        assertThat(exceptionResponse.getStatus()).isEqualTo(expectedStatus);
        assertThat(exceptionResponse.getTimestamp()).isEqualTo(expectedTimestamp);
    }
}