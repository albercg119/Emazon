package com.Emazon.Stock.configuration;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void responseKeyConstants_ShouldHaveCorrectValues() {
        assertEquals("message", Constants.MESSAGE_FIELD);
        assertEquals("status", Constants.STATUS_FIELD);
        assertEquals("timestamp", Constants.TIMESTAMP_FIELD);
    }

    @Test
    void statusConstants_ShouldHaveCorrectValues() {
        assertEquals("409 CONFLICT", Constants.CONFLICT);
        assertEquals("404 NOT_FOUND", Constants.NOT_FOUND);
        assertEquals("400 BAD_REQUEST", Constants.BAD_REQUEST);
        assertEquals("500 INTERNAL_SERVER_ERROR", Constants.INTERNAL_SERVER_ERROR);
    }

    @Test
    void exceptionMessageConstants_ShouldHaveCorrectValues() {
        assertEquals("No se encontraron datos en la base de datos", Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertEquals("El elemento indicado no existe", Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertEquals("La categoría que intentas crear ya existe", Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("El campo %s no puede estar vacío", Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertEquals("El campo %s no puede tener valores negativos", Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        assertEquals("%s", Constants.VALIDATION_ERROR_FORMAT);
        assertEquals("Ha ocurrido un error inesperado", Constants.UNEXPECTED_ERROR);
    }

    @Test
    void fieldConstants_ShouldHaveCorrectValues() {
        assertEquals("id", Constants.FIELD_ID);
        assertEquals("name", Constants.FIELD_NAME);
        assertEquals("description", Constants.FIELD_DESCRIPTION);
    }
}