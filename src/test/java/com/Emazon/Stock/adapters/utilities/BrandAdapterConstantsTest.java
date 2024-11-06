package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class BrandAdapterConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<BrandAdapterConstants> constructor = BrandAdapterConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals(BrandAdapterConstants.UTILITY_CLASS_ERROR, cause.getMessage());
    }

    @Test
    void fieldConstants_ShouldHaveCorrectValues() {
        assertEquals("nombre", BrandAdapterConstants.NAME_FIELD);
    }

    @Test
    void defaultPageConstants_ShouldHaveCorrectValues() {
        assertEquals(10, BrandAdapterConstants.DEFAULT_PAGE_SIZE);
        assertEquals(0, BrandAdapterConstants.DEFAULT_PAGE_NUMBER);
    }

    @Test
    void errorMessages_ShouldHaveCorrectValues() {
        assertEquals("No se encontraron marcas", BrandAdapterConstants.BRAND_NOT_FOUND_MESSAGE);
        assertEquals("La marca ya existe", BrandAdapterConstants.BRAND_ALREADY_EXISTS_MESSAGE);
        assertEquals("La dirección de ordenamiento no es válida", BrandAdapterConstants.INVALID_SORT_DIRECTION_MESSAGE);
        assertEquals("El tamaño de página debe ser mayor que 0", BrandAdapterConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertEquals("El número de página debe ser mayor o igual a 0", BrandAdapterConstants.INVALID_PAGE_NUMBER_MESSAGE);
    }

    @Test
    void constantsShouldNotBeNull() {
        assertNotNull(BrandAdapterConstants.NAME_FIELD);
        assertNotNull(BrandAdapterConstants.BRAND_NOT_FOUND_MESSAGE);
        assertNotNull(BrandAdapterConstants.BRAND_ALREADY_EXISTS_MESSAGE);
        assertNotNull(BrandAdapterConstants.INVALID_SORT_DIRECTION_MESSAGE);
        assertNotNull(BrandAdapterConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertNotNull(BrandAdapterConstants.INVALID_PAGE_NUMBER_MESSAGE);
    }

    @Test
    void classShouldBeFinal() {
        assertTrue(Modifier.isFinal(BrandAdapterConstants.class.getModifiers()));
    }
}