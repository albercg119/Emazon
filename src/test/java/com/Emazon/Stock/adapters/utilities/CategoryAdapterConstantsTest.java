package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryAdapterConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryAdapterConstants> constructor = CategoryAdapterConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void fieldConstants_ShouldHaveCorrectValues() {
        assertEquals("nombre", CategoryAdapterConstants.NAME_FIELD);
    }

    @Test
    void defaultPageConstants_ShouldHaveCorrectValues() {
        assertEquals(10, CategoryAdapterConstants.DEFAULT_PAGE_SIZE);
        assertEquals(0, CategoryAdapterConstants.DEFAULT_PAGE_NUMBER);
    }

    @Test
    void messageConstants_ShouldHaveCorrectValues() {
        assertEquals("No se encontraron categorías", CategoryAdapterConstants.CATEGORY_NOT_FOUND_MESSAGE);
        assertEquals("La categoría ya existe", CategoryAdapterConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
        assertEquals("La dirección de ordenamiento no es válida", CategoryAdapterConstants.INVALID_SORT_DIRECTION_MESSAGE);
        assertEquals("El tamaño de página debe ser mayor que 0", CategoryAdapterConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertEquals("El número de página debe ser mayor o igual a 0", CategoryAdapterConstants.INVALID_PAGE_NUMBER_MESSAGE);
    }
}