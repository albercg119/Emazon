package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryConstantsTest {

    @Test
    void constantValues_ShouldBeCorrect() {
        // Method name
        assertEquals("findByNombre", ArticleRepositoryConstants.FIND_BY_NOMBRE_METHOD);

        // Field names
        assertEquals("nombre", ArticleRepositoryConstants.NOMBRE_PARAM);

        // EntityGraph paths
        assertEquals("categories", ArticleRepositoryConstants.CATEGORIES_ATTRIBUTE);
        assertEquals("brand", ArticleRepositoryConstants.BRAND_ATTRIBUTE);

        // Error message
        assertEquals("Utility class", ArticleRepositoryConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constructor_ShouldBePrivate() throws Exception {
        Constructor<ArticleRepositoryConstants> constructor =
                ArticleRepositoryConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        // Verificar que la causa es IllegalStateException y tiene el mensaje correcto
        Throwable cause = exception.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals(ArticleRepositoryConstants.UTILITY_CLASS_ERROR, cause.getMessage());
    }

    @Test
    void class_ShouldBeFinal() {
        assertTrue(Modifier.isFinal(ArticleRepositoryConstants.class.getModifiers()));
    }

    @Test
    void constants_ShouldBeFinalAndStatic() {
        Field[] fields = ArticleRepositoryConstants.class.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("$VALUES")) {
                int modifiers = field.getModifiers();
                assertTrue(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers),
                        "Field " + field.getName() + " should be static and final");
                assertTrue(Modifier.isPublic(modifiers) || Modifier.isPrivate(modifiers),
                        "Field " + field.getName() + " should be public or private");
            }
        }
    }
}