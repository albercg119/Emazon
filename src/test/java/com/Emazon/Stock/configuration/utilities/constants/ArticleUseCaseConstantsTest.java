package com.Emazon.Stock.configuration.utilities.constants;

import com.Emazon.Stock.domain.utilities.constants.ArticleUseCaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ArticleUseCaseConstantsTest {

    @Test
    void verifyValidationMessages() {
        Assertions.assertEquals("El artículo no puede tener campos vacíos o nulos",
                ArticleUseCaseConstants.EMPTY_FIELDS_MESSAGE);
        assertEquals("Hay categorías duplicadas en la lista",
                ArticleUseCaseConstants.DUPLICATE_CATEGORIES_MESSAGE);
        assertEquals("El artículo debe tener entre 1 y 3 categorías",
                ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE);
        assertEquals("El nombre del artículo debe ser único",
                ArticleUseCaseConstants.ARTICLE_NAME_UNIQUE_MESSAGE);
        assertEquals("El precio del artículo debe ser mayor que cero",
                ArticleUseCaseConstants.INVALID_PRICE_MESSAGE);
        assertEquals("El stock del artículo debe ser mayor que cero",
                ArticleUseCaseConstants.INVALID_STOCK_MESSAGE);
    }

    @Test
    void verifyValidationLimits() {
        assertEquals(1, ArticleUseCaseConstants.MIN_CATEGORIES);
        assertEquals(3, ArticleUseCaseConstants.MAX_CATEGORIES);
        assertEquals(0.0, ArticleUseCaseConstants.MIN_PRICE);
        assertEquals(0, ArticleUseCaseConstants.MIN_STOCK);
    }

    @Test
    void verifyUtilityConstants() {
        assertEquals("Clase de utilidad", ArticleUseCaseConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constructorShouldBePrivate() {
        Constructor<ArticleUseCaseConstants> constructor = null;
        try {
            constructor = ArticleUseCaseConstants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "El constructor debería ser privado");
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }

    @Test
    void constructorShouldThrowException() {
        Constructor<ArticleUseCaseConstants> constructor = null;
        try {
            constructor = ArticleUseCaseConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

            Throwable cause = exception.getCause();
            assertNotNull(cause, "La causa de la excepción no debería ser nula");
            assertEquals(IllegalStateException.class, cause.getClass(),
                    "Debería ser una IllegalStateException");
            assertEquals(ArticleUseCaseConstants.UTILITY_CLASS_ERROR, cause.getMessage(),
                    "El mensaje de error debería coincidir");
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }

    @Test
    void verifyMessageFormat() {
        assertTrue(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE.contains("entre 1 y 3"),
                "El mensaje debería contener los límites de categorías");
        assertTrue(ArticleUseCaseConstants.INVALID_PRICE_MESSAGE.contains("mayor que cero"),
                "El mensaje de precio debería mencionar 'mayor que cero'");
    }
}