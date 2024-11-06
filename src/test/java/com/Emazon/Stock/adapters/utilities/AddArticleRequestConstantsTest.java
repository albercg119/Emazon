package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class AddArticleRequestConstantsTest {

    @Test
    void verificarMensajesDeValidacion() {
        assertEquals("El nombre del artículo no puede ser nulo",
                AddArticleRequestConstants.NAME_NOT_NULL_MESSAGE);
        assertEquals("El nombre del artículo es requerido",
                AddArticleRequestConstants.NAME_NOT_BLANK_MESSAGE);
        assertEquals("La descripción del artículo no puede ser nula",
                AddArticleRequestConstants.DESCRIPTION_NOT_NULL_MESSAGE);
        assertEquals("La descripción del artículo es requerida",
                AddArticleRequestConstants.DESCRIPTION_NOT_BLANK_MESSAGE);
        assertEquals("El stock del artículo no puede ser nulo",
                AddArticleRequestConstants.STOCK_NOT_NULL_MESSAGE);
        assertEquals("El precio del artículo no puede ser nulo",
                AddArticleRequestConstants.PRICE_NOT_NULL_MESSAGE);
        assertEquals("Las categorías del artículo no pueden ser nulas",
                AddArticleRequestConstants.CATEGORIES_NOT_NULL_MESSAGE);
        assertEquals("La marca del artículo no puede ser nula",
                AddArticleRequestConstants.BRAND_NOT_NULL_MESSAGE);

        String expectedSizeMessage = String.format(AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE, 1, 90);
        assertTrue(expectedSizeMessage.contains("1"));
        assertTrue(expectedSizeMessage.contains("90"));

        String expectedPriceMessage = String.format(AddArticleRequestConstants.PRICE_DECIMAL_MIN_MESSAGE, "0.0");
        assertTrue(expectedPriceMessage.contains("0.0"));
    }

    @Test
    void verificarValoresDeRestricciones() {
        assertEquals(1, AddArticleRequestConstants.NAME_MIN_LENGTH);
        assertEquals(50, AddArticleRequestConstants.NAME_MAX_LENGTH);
        assertEquals(1, AddArticleRequestConstants.DESCRIPTION_MIN_LENGTH);
        assertEquals(90, AddArticleRequestConstants.DESCRIPTION_MAX_LENGTH);
        assertEquals("0.0", AddArticleRequestConstants.MIN_PRICE);
    }

    @Test
    void verificarMensajesDeTamanio() {
        String tamañoNombreEsperado = String.format(AddArticleRequestConstants.NAME_SIZE_MESSAGE,
                AddArticleRequestConstants.NAME_MIN_LENGTH,
                AddArticleRequestConstants.NAME_MAX_LENGTH);
        assertTrue(tamañoNombreEsperado.contains("1"));
        assertTrue(tamañoNombreEsperado.contains("50"));

        String tamañoDescripcionEsperado = String.format(AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE,
                AddArticleRequestConstants.DESCRIPTION_MIN_LENGTH,
                AddArticleRequestConstants.DESCRIPTION_MAX_LENGTH);
        assertTrue(tamañoDescripcionEsperado.contains("1"));
        assertTrue(tamañoDescripcionEsperado.contains("90"));
    }

    @Test
    void elConstructorDeberiaSerPrivado() {
        Constructor<AddArticleRequestConstants> constructor = null;
        try {
            constructor = AddArticleRequestConstants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            Exception excepcion = assertThrows(InvocationTargetException.class, constructor::newInstance);
            assertEquals(IllegalStateException.class, excepcion.getCause().getClass());
            assertEquals(AddArticleRequestConstants.UTILITY_CLASS_ERROR, excepcion.getCause().getMessage());
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }
}