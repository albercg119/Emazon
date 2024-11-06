package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ArticleMapperConstantsTest {

    @Test
    void verifyMappingSourceFields() {
        assertEquals("id", ArticleMapperConstants.ID_FIELD);
        assertEquals("name", ArticleMapperConstants.NAME_FIELD);
        assertEquals("description", ArticleMapperConstants.DESCRIPTION_FIELD);
        assertEquals("price", ArticleMapperConstants.PRICE_FIELD);
        assertEquals("brand.nombre", ArticleMapperConstants.BRAND_NOMBRE_FIELD);
        assertEquals("categories", ArticleMapperConstants.CATEGORIES_FIELD);
        assertEquals("categoryIds", ArticleMapperConstants.CATEGORY_IDS_FIELD);
        assertEquals("brandId", ArticleMapperConstants.BRAND_ID_FIELD);
    }

    @Test
    void verifyMappingTargetFields() {
        assertEquals("brandName", ArticleMapperConstants.BRAND_NAME_TARGET);
        assertEquals("brand", ArticleMapperConstants.BRAND_TARGET);
        assertEquals("categories", ArticleMapperConstants.CATEGORIES_TARGET);
    }

    @Test
    void verifyQualifiedMapperNames() {
        assertEquals("mapCategories", ArticleMapperConstants.MAP_CATEGORIES_METHOD);
        assertEquals("mapCategoryIds", ArticleMapperConstants.MAP_CATEGORY_IDS_METHOD);
        assertEquals("mapBrandId", ArticleMapperConstants.MAP_BRAND_ID_METHOD);
    }

    @Test
    void verifyErrorMessages() {
        assertEquals("Error mapping categories", ArticleMapperConstants.CATEGORY_MAPPING_ERROR);
        assertEquals("Error mapping brand", ArticleMapperConstants.BRAND_MAPPING_ERROR);
    }

    @Test
    void verifyUtilityConstants() {
        assertEquals("Utility class", ArticleMapperConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constructorShouldBePrivate() {
        Constructor<ArticleMapperConstants> constructor = null;
        try {
            constructor = ArticleMapperConstants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "Constructor should be private");
        } catch (NoSuchMethodException e) {
            fail("Constructor not found");
        }
    }

    @Test
    void constructorShouldThrowException() {
        Constructor<ArticleMapperConstants> constructor = null;
        try {
            constructor = ArticleMapperConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Exception exception = assertThrows(InvocationTargetException.class,
                    constructor::newInstance,
                    "Constructor should throw exception");

            Throwable cause = exception.getCause();
            assertNotNull(cause, "Exception cause should not be null");
            assertEquals(IllegalStateException.class, cause.getClass(),
                    "Should be IllegalStateException");
            assertEquals(ArticleMapperConstants.UTILITY_CLASS_ERROR, cause.getMessage(),
                    "Should have correct error message");
        } catch (NoSuchMethodException e) {
            fail("Constructor not found");
        }
    }

    @Test
    void constantsShouldNotBeNull() {
        // Source fields
        assertNotNull(ArticleMapperConstants.ID_FIELD);
        assertNotNull(ArticleMapperConstants.NAME_FIELD);
        assertNotNull(ArticleMapperConstants.DESCRIPTION_FIELD);
        assertNotNull(ArticleMapperConstants.PRICE_FIELD);
        assertNotNull(ArticleMapperConstants.BRAND_NOMBRE_FIELD);
        assertNotNull(ArticleMapperConstants.CATEGORIES_FIELD);
        assertNotNull(ArticleMapperConstants.CATEGORY_IDS_FIELD);
        assertNotNull(ArticleMapperConstants.BRAND_ID_FIELD);

        // Target fields
        assertNotNull(ArticleMapperConstants.BRAND_NAME_TARGET);
        assertNotNull(ArticleMapperConstants.BRAND_TARGET);
        assertNotNull(ArticleMapperConstants.CATEGORIES_TARGET);

        // Mapper names
        assertNotNull(ArticleMapperConstants.MAP_CATEGORIES_METHOD);
        assertNotNull(ArticleMapperConstants.MAP_CATEGORY_IDS_METHOD);
        assertNotNull(ArticleMapperConstants.MAP_BRAND_ID_METHOD);

        // Error messages
        assertNotNull(ArticleMapperConstants.CATEGORY_MAPPING_ERROR);
        assertNotNull(ArticleMapperConstants.BRAND_MAPPING_ERROR);

        // Utility
        assertNotNull(ArticleMapperConstants.UTILITY_CLASS_ERROR);
    }

    @Test
    void constantsShouldBeUnique() {
        // Verificar que los campos target no se repitan con los source
        assertNotEquals(ArticleMapperConstants.BRAND_NAME_TARGET, ArticleMapperConstants.BRAND_NOMBRE_FIELD);
        assertNotEquals(ArticleMapperConstants.BRAND_TARGET, ArticleMapperConstants.BRAND_ID_FIELD);
    }

    @Test
    void verifyErrorMessagesFormat() {
        assertTrue(ArticleMapperConstants.CATEGORY_MAPPING_ERROR.contains("mapping"));
        assertTrue(ArticleMapperConstants.BRAND_MAPPING_ERROR.contains("mapping"));
    }
}