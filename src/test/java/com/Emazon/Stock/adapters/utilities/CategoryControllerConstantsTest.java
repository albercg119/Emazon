package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryControllerConstants> constructor = CategoryControllerConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
            fail("Se esperaba que el constructor lanzara IllegalStateException");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalStateException);
            assertEquals("Utility class", cause.getMessage());
        } catch (InstantiationException | IllegalAccessException e) {
            fail("No se esperaba esta excepción: " + e);
        }
    }

    @Test
    void pathConstants_ShouldHaveCorrectValues() {
        assertEquals("/category", CategoryControllerConstants.CATEGORY_BASE_PATH);
        assertEquals("/paged", CategoryControllerConstants.CATEGORY_PAGED_PATH);
    }

    @Test
    void operationSummaryConstants_ShouldHaveCorrectValues() {
        assertEquals("Crear una nueva categoría", CategoryControllerConstants.CATEGORY_CREATED_SUMMARY);
        assertEquals("Obtener categorías paginadas", CategoryControllerConstants.CATEGORIES_PAGED_SUMMARY);
        assertEquals("Obtener todas las categorías", CategoryControllerConstants.CATEGORIES_UNPAGED_SUMMARY);
    }

    @Test
    void responseCodeConstants_ShouldHaveCorrectValues() {
        assertEquals("201", CategoryControllerConstants.CATEGORY_SUCCESS_CODE);
        assertEquals("200", CategoryControllerConstants.CATEGORIES_FOUND_CODE);
    }

    @Test
    void responseMessageConstants_ShouldHaveCorrectValues() {
        assertEquals("Categoría creada exitosamente", CategoryControllerConstants.CATEGORY_CREATED_SUCCESSFULLY);
        assertEquals("Categorías encontradas exitosamente", CategoryControllerConstants.CATEGORIES_FOUND);
    }

    @Test
    void requestParameterConstants_ShouldHaveCorrectValues() {
        assertEquals("page", CategoryControllerConstants.PARAM_PAGE);
        assertEquals("size", CategoryControllerConstants.PARAM_SIZE);
        assertEquals("sort", CategoryControllerConstants.PARAM_SORT);
    }

    @Test
    void defaultValueConstants_ShouldHaveCorrectValues() {
        assertEquals("0", CategoryControllerConstants.DEFAULT_PAGE);
        assertEquals("10", CategoryControllerConstants.DEFAULT_SIZE);
        assertEquals("asc", CategoryControllerConstants.DEFAULT_SORT);
    }

    @Test
    void sortValueConstants_ShouldHaveCorrectValues() {
        assertEquals("asc", CategoryControllerConstants.SORT_ASCENDING);
        assertEquals("desc", CategoryControllerConstants.SORT_DESCENDING);
    }
}