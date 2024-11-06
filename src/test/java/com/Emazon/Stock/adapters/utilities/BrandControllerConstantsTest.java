package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandControllerConstantsTest {

    @Test
    void verifyResponseMessages() {
        assertEquals("Marca creada exitosamente", BrandControllerConstants.BRAND_CREATED_SUCCESSFULLY,
                "El mensaje de creación exitosa debe coincidir");
        assertEquals("Marcas encontradas", BrandControllerConstants.BRANDS_FOUND,
                "El mensaje de marcas encontradas debe coincidir");
    }

    @Test
    void verifySwaggerSummaries() {
        assertEquals("Crear nueva marca", BrandControllerConstants.BRAND_CREATED_SUMMARY,
                "El resumen de creación debe coincidir");
        assertEquals("Obtener marcas paginadas", BrandControllerConstants.BRANDS_PAGED_SUMMARY,
                "El resumen de paginación debe coincidir");
        assertEquals("Obtener todas las marcas", BrandControllerConstants.BRANDS_UNPAGED_SUMMARY,
                "El resumen de obtener todas debe coincidir");
    }

    @Test
    void verifyHttpStatusCodes() {
        assertEquals("201", BrandControllerConstants.BRAND_SUCCESS_CODE,
                "El código de éxito debe ser 201");
        assertEquals("200", BrandControllerConstants.BRANDS_FOUND_CODE,
                "El código de encontrado debe ser 200");
    }

    @Test
    void verifyPaginationParameters() {
        assertEquals("page", BrandControllerConstants.PARAM_PAGE,
                "El parámetro de página debe coincidir");
        assertEquals("size", BrandControllerConstants.PARAM_SIZE,
                "El parámetro de tamaño debe coincidir");
        assertEquals("sort", BrandControllerConstants.PARAM_SORT,
                "El parámetro de ordenamiento debe coincidir");
    }

    @Test
    void verifySortingConstants() {
        assertEquals("asc", BrandControllerConstants.SORT_ASCENDING,
                "El valor de ordenamiento ascendente debe ser 'asc'");
        assertSame(BrandControllerConstants.SORT_ASCENDING, BrandControllerConstants.DEFAULT_SORT,
                "El ordenamiento por defecto debe ser el mismo objeto que SORT_ASCENDING");
    }

    @Test
    void verifyDefaultValues() {
        assertEquals("0", BrandControllerConstants.DEFAULT_PAGE,
                "La página por defecto debe ser 0");
        assertEquals("10", BrandControllerConstants.DEFAULT_SIZE,
                "El tamaño por defecto debe ser 10");
    }

    @Test
    void verifyNumericDefaultValues() {
        // Verifica que los valores por defecto sean números válidos
        assertDoesNotThrow(() -> Integer.parseInt(BrandControllerConstants.DEFAULT_PAGE),
                "DEFAULT_PAGE debe ser un número válido");
        assertDoesNotThrow(() -> Integer.parseInt(BrandControllerConstants.DEFAULT_SIZE),
                "DEFAULT_SIZE debe ser un número válido");

        assertTrue(Integer.parseInt(BrandControllerConstants.DEFAULT_PAGE) >= 0,
                "DEFAULT_PAGE debe ser no negativo");
        assertTrue(Integer.parseInt(BrandControllerConstants.DEFAULT_SIZE) > 0,
                "DEFAULT_SIZE debe ser positivo");
    }

    @Test
    void verifyStatusCodesFormat() {
        assertTrue(BrandControllerConstants.BRAND_SUCCESS_CODE.matches("\\d{3}"),
                "El código de éxito debe tener formato de 3 dígitos");
        assertTrue(BrandControllerConstants.BRANDS_FOUND_CODE.matches("\\d{3}"),
                "El código de encontrado debe tener formato de 3 dígitos");
    }
}