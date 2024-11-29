package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

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
        assertEquals(CategoryAdapterConstants.UTILITY_CLASS_ERROR, cause.getMessage());
    }

    @Test
    void fieldNames_ShouldHaveCorrectValues() {
        assertEquals("nombre", CategoryAdapterConstants.NAME_FIELD);
    }

    @Test
    void defaultPaginationValues_ShouldHaveCorrectValues() {
        assertEquals(10, CategoryAdapterConstants.DEFAULT_PAGE_SIZE);
        assertEquals(0, CategoryAdapterConstants.DEFAULT_PAGE_NUMBER);
    }

    @Test
    void errorMessages_ShouldHaveCorrectValues() {
        assertEquals("No se encontraron categorías", CategoryAdapterConstants.CATEGORY_NOT_FOUND_MESSAGE);
        assertEquals("La categoría ya existe", CategoryAdapterConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
        assertEquals("La dirección de ordenamiento no es válida", CategoryAdapterConstants.INVALID_SORT_DIRECTION_MESSAGE);
        assertEquals("El tamaño de página debe ser mayor que 0", CategoryAdapterConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertEquals("El número de página debe ser mayor o igual a 0", CategoryAdapterConstants.INVALID_PAGE_NUMBER_MESSAGE);
    }

    @Test
    void constantsShouldNotBeNull() {
        assertNotNull(CategoryAdapterConstants.NAME_FIELD);
        assertNotNull(CategoryAdapterConstants.CATEGORY_NOT_FOUND_MESSAGE);
        assertNotNull(CategoryAdapterConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
        assertNotNull(CategoryAdapterConstants.INVALID_SORT_DIRECTION_MESSAGE);
        assertNotNull(CategoryAdapterConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertNotNull(CategoryAdapterConstants.INVALID_PAGE_NUMBER_MESSAGE);
        assertNotNull(CategoryAdapterConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void classShouldBeFinal() {
        assertTrue(Modifier.isFinal(CategoryAdapterConstants.class.getModifiers()));
    }

    @Test
    void paginationValues_ShouldBeValid() {
        assertTrue(CategoryAdapterConstants.DEFAULT_PAGE_SIZE > 0);
        assertTrue(CategoryAdapterConstants.DEFAULT_PAGE_NUMBER >= 0);
    }
}