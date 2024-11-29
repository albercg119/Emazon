package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddBrandRequestConstantsTest {

    @Test
    void errorMessages_ShouldHaveCorrectValues() {
        assertEquals("El nombre de la marca es requerido", AddBrandRequestConstants.BRAND_NAME_REQUIRED);
        assertEquals("El nombre de la marca no puede exceder los 50 caracteres", AddBrandRequestConstants.BRAND_NAME_MAX_SIZE);
        assertEquals("La descripción de la marca es requerida", AddBrandRequestConstants.BRAND_DESCRIPTION_REQUIRED);
        assertEquals("La descripción de la marca no puede exceder los 120 caracteres", AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_SIZE);
    }

    @Test
    void maxLengthValues_ShouldHaveCorrectValues() {
        assertEquals(50, AddBrandRequestConstants.BRAND_NAME_MAX_LENGTH);
        assertEquals(120, AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_LENGTH);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Error messages
        assertNotNull(AddBrandRequestConstants.BRAND_NAME_REQUIRED);
        assertNotNull(AddBrandRequestConstants.BRAND_NAME_MAX_SIZE);
        assertNotNull(AddBrandRequestConstants.BRAND_DESCRIPTION_REQUIRED);
        assertNotNull(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_SIZE);

        // Max length values
        assertNotNull(AddBrandRequestConstants.BRAND_NAME_MAX_LENGTH);
        assertNotNull(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_LENGTH);
    }

    @Test
    void maxLengthValues_ShouldBePositive() {
        assertTrue(AddBrandRequestConstants.BRAND_NAME_MAX_LENGTH > 0);
        assertTrue(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_LENGTH > 0);
    }

    @Test
    void descriptionMaxLength_ShouldBeGreaterThanNameMaxLength() {
        assertTrue(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_LENGTH > AddBrandRequestConstants.BRAND_NAME_MAX_LENGTH);
    }

    @Test
    void errorMessages_ShouldContainRelevantTerms() {
        assertTrue(AddBrandRequestConstants.BRAND_NAME_REQUIRED.toLowerCase().contains("nombre"));
        assertTrue(AddBrandRequestConstants.BRAND_NAME_MAX_SIZE.toLowerCase().contains("nombre"));
        assertTrue(AddBrandRequestConstants.BRAND_DESCRIPTION_REQUIRED.toLowerCase().contains("descripción"));
        assertTrue(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_SIZE.toLowerCase().contains("descripción"));
    }

    @Test
    void maxSizeMessages_ShouldIncludeCorrectLimits() {
        assertTrue(AddBrandRequestConstants.BRAND_NAME_MAX_SIZE.contains(String.valueOf(AddBrandRequestConstants.BRAND_NAME_MAX_LENGTH)));
        assertTrue(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_SIZE.contains(String.valueOf(AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_LENGTH)));
    }
}