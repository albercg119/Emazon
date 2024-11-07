package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ArticleEntityMapperConstantsTest {

    @Test
    void constantValues_ShouldBeCorrect() {
        // Source fields (English)
        assertEquals("name", ArticleEntityMapperConstants.NAME_FIELD);
        assertEquals("description", ArticleEntityMapperConstants.DESCRIPTION_FIELD);
        assertEquals("stockQuantity", ArticleEntityMapperConstants.STOCK_QUANTITY_FIELD);
        assertEquals("price", ArticleEntityMapperConstants.PRICE_FIELD);
        assertEquals("brand", ArticleEntityMapperConstants.BRAND_FIELD);
        assertEquals("categories", ArticleEntityMapperConstants.CATEGORIES_FIELD);

        // Target fields (Spanish)
        assertEquals("nombre", ArticleEntityMapperConstants.NOMBRE_FIELD);
        assertEquals("descripcion", ArticleEntityMapperConstants.DESCRIPCION_FIELD);

        // Error message
        assertEquals("Utility class", ArticleEntityMapperConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constructor_ShouldBePrivate() throws Exception {
        Constructor<ArticleEntityMapperConstants> constructor =
                ArticleEntityMapperConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        try {
            constructor.newInstance();
            fail("Should have thrown an exception");
        } catch (Exception e) {
            // Verificar que la causa raíz es IllegalStateException
            Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalStateException);
            assertEquals(ArticleEntityMapperConstants.UTILITY_CLASS_ERROR, cause.getMessage());
        }
    }

    @Test
    void class_ShouldBeFinal() {
        assertTrue(Modifier.isFinal(ArticleEntityMapperConstants.class.getModifiers()));
    }

    @Test
    void constants_ShouldBeFinalAndStatic() {
        Field[] fields = ArticleEntityMapperConstants.class.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("$VALUES")) { // Exclude enum synthetic field
                int modifiers = field.getModifiers();
                assertTrue(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers),
                        "Field " + field.getName() + " should be static and final");
                assertTrue(Modifier.isPublic(modifiers) || Modifier.isPrivate(modifiers),
                        "Field " + field.getName() + " should be public or private");
            }
        }
    }
}