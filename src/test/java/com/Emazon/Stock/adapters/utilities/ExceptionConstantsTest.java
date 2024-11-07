package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<ExceptionConstants> constructor = ExceptionConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void messageConstants_ShouldHaveCorrectValues() {
        assertEquals("No data found", ExceptionConstants.NO_DATA_FOUND_MESSAGE);
        assertEquals("Element not found", ExceptionConstants.ELEMENT_NOT_FOUND_MESSAGE);
        assertEquals("Category already exists", ExceptionConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
    }
}