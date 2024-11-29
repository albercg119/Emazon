package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryEntityConstants> constructor = CategoryEntityConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void tableConstants_ShouldHaveCorrectValues() {
        assertEquals("category", CategoryEntityConstants.TABLE_NAME);
        assertEquals("nombre", CategoryEntityConstants.NOMBRE_COLUMN);
    }
}