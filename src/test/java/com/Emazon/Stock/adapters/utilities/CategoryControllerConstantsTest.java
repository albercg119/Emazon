package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerConstantsTest {

    @Test
    void verifyResponseMessages() {
        assertEquals("Categoría creada exitosamente", CategoryControllerConstants.CATEGORY_CREATED_SUCCESSFULLY,
                "El mensaje de creación exitosa debe coincidir");
        assertEquals("Categorías encontradas", CategoryControllerConstants.CATEGORIES_FOUND,
                "El mensaje de categorías encontradas debe coincidir");
    }

    @Test
    void verifySwaggerSummaries() {
        assertEquals("Crear nueva categoría", CategoryControllerConstants.CATEGORY_CREATED_SUMMARY,
                "El resumen de creación debe coincidir");
        assertEquals("Obtener categorías paginadas", CategoryControllerConstants.CATEGORIES_PAGED_SUMMARY,
                "El resumen de paginación debe coincidir");
        assertEquals("Obtener todas las categorías", CategoryControllerConstants.CATEGORIES_UNPAGED_SUMMARY,
                "El resumen de obtener todas debe coincidir");
    }

    @Test
    void verifyHttpStatusCodes() {
        assertEquals("201", CategoryControllerConstants.CATEGORY_SUCCESS_CODE,
                "El código de éxito debe ser 201");
        assertEquals("200", CategoryControllerConstants.CATEGORIES_FOUND_CODE,
                "El código de encontrado debe ser 200");
    }

    @Test
    void verifyPaginationParameters() {
        assertEquals("page", CategoryControllerConstants.PARAM_PAGE,
                "El parámetro de página debe coincidir");
        assertEquals("size", CategoryControllerConstants.PARAM_SIZE,
                "El parámetro de tamaño debe coincidir");
        assertEquals("sort", CategoryControllerConstants.PARAM_SORT,
                "El parámetro de ordenamiento debe coincidir");
    }

    @Test
    void verifySortingConstants() {
        assertEquals("asc", CategoryControllerConstants.SORT_ASCENDING,
                "El valor de ordenamiento ascendente debe ser 'asc'");
        assertSame(CategoryControllerConstants.SORT_ASCENDING, CategoryControllerConstants.DEFAULT_SORT,
                "El ordenamiento por defecto debe ser el mismo objeto que SORT_ASCENDING");
    }

    @Test
    void verifyDefaultValues() {
        assertEquals("0", CategoryControllerConstants.DEFAULT_PAGE,
                "La página por defecto debe ser 0");
        assertEquals("10", CategoryControllerConstants.DEFAULT_SIZE,
                "El tamaño por defecto debe ser 10");
    }

    @Test
    void verifyNumericDefaultValues() {
        // Verifica que los valores por defecto sean números válidos
        assertDoesNotThrow(() -> Integer.parseInt(CategoryControllerConstants.DEFAULT_PAGE),
                "DEFAULT_PAGE debe ser un número válido");
        assertDoesNotThrow(() -> Integer.parseInt(CategoryControllerConstants.DEFAULT_SIZE),
                "DEFAULT_SIZE debe ser un número válido");

        assertTrue(Integer.parseInt(CategoryControllerConstants.DEFAULT_PAGE) >= 0,
                "DEFAULT_PAGE debe ser no negativo");
        assertTrue(Integer.parseInt(CategoryControllerConstants.DEFAULT_SIZE) > 0,
                "DEFAULT_SIZE debe ser positivo");
    }

    @Test
    void verifyStatusCodesFormat() {
        assertTrue(CategoryControllerConstants.CATEGORY_SUCCESS_CODE.matches("\\d{3}"),
                "El código de éxito debe tener formato de 3 dígitos");
        assertTrue(CategoryControllerConstants.CATEGORIES_FOUND_CODE.matches("\\d{3}"),
                "El código de encontrado debe tener formato de 3 dígitos");
    }
}