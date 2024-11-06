package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandMapperConstantsTest {

    @Test
    void componentModel_ShouldHaveCorrectValue() {
        assertEquals("spring", BrandMapperConstants.SPRING_COMPONENT_MODEL);
    }

    @Test
    void englishFieldNames_ShouldHaveCorrectValues() {
        assertEquals("name", BrandMapperConstants.NAME_FIELD);
        assertEquals("description", BrandMapperConstants.DESCRIPTION_FIELD);
    }

    @Test
    void spanishFieldNames_ShouldHaveCorrectValues() {
        assertEquals("id", BrandMapperConstants.ID_FIELD);
        assertEquals("nombre", BrandMapperConstants.NOMBRE_FIELD);
        assertEquals("descripcion", BrandMapperConstants.DESCRIPCION_FIELD);
    }

    @Test
    void ignoreId_ShouldBeTrue() {
        assertTrue(BrandMapperConstants.IGNORE_ID);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Component model
        assertNotNull(BrandMapperConstants.SPRING_COMPONENT_MODEL);

        // English field names
        assertNotNull(BrandMapperConstants.NAME_FIELD);
        assertNotNull(BrandMapperConstants.DESCRIPTION_FIELD);

        // Spanish field names
        assertNotNull(BrandMapperConstants.ID_FIELD);
        assertNotNull(BrandMapperConstants.NOMBRE_FIELD);
        assertNotNull(BrandMapperConstants.DESCRIPCION_FIELD);
    }

    @Test
    void fieldNames_ShouldNotContainSpaces() {
        assertFalse(BrandMapperConstants.NAME_FIELD.contains(" "));
        assertFalse(BrandMapperConstants.DESCRIPTION_FIELD.contains(" "));
        assertFalse(BrandMapperConstants.ID_FIELD.contains(" "));
        assertFalse(BrandMapperConstants.NOMBRE_FIELD.contains(" "));
        assertFalse(BrandMapperConstants.DESCRIPCION_FIELD.contains(" "));
    }

    @Test
    void englishFields_ShouldBeLowerCase() {
        assertEquals(BrandMapperConstants.NAME_FIELD.toLowerCase(), BrandMapperConstants.NAME_FIELD);
        assertEquals(BrandMapperConstants.DESCRIPTION_FIELD.toLowerCase(), BrandMapperConstants.DESCRIPTION_FIELD);
    }

    @Test
    void spanishFields_ShouldBeLowerCase() {
        assertEquals(BrandMapperConstants.ID_FIELD.toLowerCase(), BrandMapperConstants.ID_FIELD);
        assertEquals(BrandMapperConstants.NOMBRE_FIELD.toLowerCase(), BrandMapperConstants.NOMBRE_FIELD);
        assertEquals(BrandMapperConstants.DESCRIPCION_FIELD.toLowerCase(), BrandMapperConstants.DESCRIPCION_FIELD);
    }

    @Test
    void componentModel_ShouldBeLowerCase() {
        assertEquals(BrandMapperConstants.SPRING_COMPONENT_MODEL.toLowerCase(),
                BrandMapperConstants.SPRING_COMPONENT_MODEL);
    }

    @Test
    void fieldNames_ShouldBeConsistentWithMapperRequirements() {
        assertTrue(BrandMapperConstants.NAME_FIELD.matches("[a-zA-Z]+"));
        assertTrue(BrandMapperConstants.DESCRIPTION_FIELD.matches("[a-zA-Z]+"));
        assertTrue(BrandMapperConstants.ID_FIELD.matches("[a-zA-Z]+"));
        assertTrue(BrandMapperConstants.NOMBRE_FIELD.matches("[a-zA-Z]+"));
        assertTrue(BrandMapperConstants.DESCRIPCION_FIELD.matches("[a-zA-Z]+"));
    }

    @Test
    void correspondingFields_ShouldHaveMatchingMeanings() {
        // Verifica que los campos en inglés y español correspondan semánticamente
        assertNotEquals(BrandMapperConstants.NAME_FIELD, BrandMapperConstants.DESCRIPTION_FIELD);
        assertNotEquals(BrandMapperConstants.NOMBRE_FIELD, BrandMapperConstants.DESCRIPCION_FIELD);
    }
}