package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ArticleMapperConstantsTest {

    @Test
    void constantValues_ShouldBeCorrect() {
        // Field names
        assertEquals("id", ArticleMapperConstants.ID_FIELD);
        assertEquals("name", ArticleMapperConstants.NAME_FIELD);
        assertEquals("description", ArticleMapperConstants.DESCRIPTION_FIELD);
        assertEquals("price", ArticleMapperConstants.PRICE_FIELD);
        assertEquals("brand.nombre", ArticleMapperConstants.BRAND_NOMBRE_FIELD);
        assertEquals("categories", ArticleMapperConstants.CATEGORIES_FIELD);
        assertEquals("categoryIds", ArticleMapperConstants.CATEGORY_IDS_FIELD);
        assertEquals("brandId", ArticleMapperConstants.BRAND_ID_FIELD);

        // Target fields
        assertEquals("brandName", ArticleMapperConstants.BRAND_NAME_TARGET);
        assertEquals("brand", ArticleMapperConstants.BRAND_TARGET);
        assertEquals("categories", ArticleMapperConstants.CATEGORIES_TARGET);

        // Method names
        assertEquals("mapCategories", ArticleMapperConstants.MAP_CATEGORIES_METHOD);
        assertEquals("mapCategoryIds", ArticleMapperConstants.MAP_CATEGORY_IDS_METHOD);
        assertEquals("mapBrandId", ArticleMapperConstants.MAP_BRAND_ID_METHOD);

        // Error messages
        assertEquals("Error mapping categories", ArticleMapperConstants.CATEGORY_MAPPING_ERROR);
        assertEquals("Error mapping brand", ArticleMapperConstants.BRAND_MAPPING_ERROR);
        assertEquals("Utility class", ArticleMapperConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constructor_ShouldBePrivate() throws Exception {
        Constructor<ArticleMapperConstants> constructor = ArticleMapperConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        // Verificar que la causa es IllegalStateException y tiene el mensaje correcto
        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals(ArticleMapperConstants.UTILITY_CLASS_ERROR, exception.getCause().getMessage());
    }

    @Test
    void class_ShouldBeFinal() {
        assertTrue(Modifier.isFinal(ArticleMapperConstants.class.getModifiers()));
    }

    @Test
    void constants_ShouldBeFinalAndStatic() {
        assertTrue(Modifier.isFinal(ArticleMapperConstants.class.getModifiers()));

        java.lang.reflect.Field[] fields = ArticleMapperConstants.class.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            int modifiers = field.getModifiers();
            assertTrue(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers),
                    "Field " + field.getName() + " should be static and final");
            assertTrue(Modifier.isPublic(modifiers) || Modifier.isPrivate(modifiers),
                    "Field " + field.getName() + " should be public or private");
        }
    }
}