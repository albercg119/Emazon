package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryRepositoryConstants> constructor = CategoryRepositoryConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void parameterConstants_ShouldHaveCorrectValues() {
        assertEquals("nombre", CategoryRepositoryConstants.PARAM_NOMBRE);
        assertEquals("id", CategoryRepositoryConstants.PARAM_ID);
    }

    @Test
    void fieldConstants_ShouldHaveCorrectValues() {
        assertEquals("id", CategoryRepositoryConstants.FIELD_ID);
        assertEquals("nombre", CategoryRepositoryConstants.FIELD_NOMBRE);
    }

    @Test
    void tableConstants_ShouldHaveCorrectValues() {
        assertEquals("categories", CategoryRepositoryConstants.CATEGORY_TABLE);
    }

    @Test
    void findByNombreQuery_ShouldBeCorrectlyFormatted() {
        String expectedQuery = "SELECT c FROM CategoryEntity c WHERE LOWER(c.nombre) = LOWER(:nombre)";
        assertEquals(expectedQuery, CategoryRepositoryConstants.FIND_BY_NOMBRE_QUERY);
    }

    @Test
    void existByNameExcludingIdQuery_ShouldBeCorrectlyFormatted() {
        String expectedQuery = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
                "FROM CategoryEntity c " +
                "WHERE LOWER(c.nombre) = LOWER(:nombre) " +
                "AND c.id != :id";
        assertEquals(expectedQuery, CategoryRepositoryConstants.EXIST_BY_NAME_EXCLUDING_ID_QUERY);
    }
}