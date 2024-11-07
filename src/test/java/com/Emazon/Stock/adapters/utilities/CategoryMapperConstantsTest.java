package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryMapperConstants> constructor = CategoryMapperConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void modelFieldConstants_ShouldHaveCorrectValues() {
        assertEquals("id", CategoryMapperConstants.MODEL_ID);
        assertEquals("nombre", CategoryMapperConstants.MODEL_NOMBRE);
        assertEquals("descripcion", CategoryMapperConstants.MODEL_DESCRIPCION);
    }

    @Test
    void dtoFieldConstants_ShouldHaveCorrectValues() {
        assertEquals("id", CategoryMapperConstants.DTO_ID);
        assertEquals("name", CategoryMapperConstants.DTO_NAME);
        assertEquals("description", CategoryMapperConstants.DTO_DESCRIPTION);
    }
}