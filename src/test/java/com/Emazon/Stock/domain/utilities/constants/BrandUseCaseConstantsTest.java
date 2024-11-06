package com.Emazon.Stock.domain.utilities.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandUseCaseConstantsTest {

    @Test
    void shouldHaveCorrectErrorMessageForNullBrand() {
        assertEquals("La marca no puede ser nula", BrandUseCaseConstants.BRAND_NULL_ERROR);
    }

    @Test
    void shouldHaveCorrectErrorMessageForNullOrEmptyName() {
        assertEquals("El nombre de la marca no puede ser nulo o vacío", BrandUseCaseConstants.BRAND_NAME_NULL_OR_EMPTY_ERROR);
    }

    @Test
    void shouldHaveCorrectErrorMessageForNameLength() {
        assertEquals("El nombre de la marca no puede exceder los 50 caracteres", BrandUseCaseConstants.BRAND_NAME_LENGTH_ERROR);
    }

    @Test
    void shouldHaveCorrectErrorMessageForNullOrEmptyDescription() {
        assertEquals("La descripción de la marca no puede ser nula o vacía", BrandUseCaseConstants.BRAND_DESCRIPTION_NULL_OR_EMPTY_ERROR);
    }

    @Test
    void shouldHaveCorrectErrorMessageForDescriptionLength() {
        assertEquals("La descripción de la marca no puede exceder los 120 caracteres", BrandUseCaseConstants.BRAND_DESCRIPTION_LENGTH_ERROR);
    }

    @Test
    void shouldHaveCorrectErrorMessageForDuplicateBrand() {
        assertEquals("El nombre de la marca ya existe en el sistema", BrandUseCaseConstants.BRAND_ALREADY_EXISTS_ERROR);
    }

    @Test
    void shouldHaveCorrectMaxLengthForBrandName() {
        assertEquals(50, BrandUseCaseConstants.BRAND_NAME_MAX_LENGTH);
    }

    @Test
    void shouldHaveCorrectMaxLengthForBrandDescription() {
        assertEquals(120, BrandUseCaseConstants.BRAND_DESCRIPTION_MAX_LENGTH);
    }

    @Test
    void constantsShouldNotBeNull() {
        assertNotNull(BrandUseCaseConstants.BRAND_NULL_ERROR);
        assertNotNull(BrandUseCaseConstants.BRAND_NAME_NULL_OR_EMPTY_ERROR);
        assertNotNull(BrandUseCaseConstants.BRAND_NAME_LENGTH_ERROR);
        assertNotNull(BrandUseCaseConstants.BRAND_DESCRIPTION_NULL_OR_EMPTY_ERROR);
        assertNotNull(BrandUseCaseConstants.BRAND_DESCRIPTION_LENGTH_ERROR);
        assertNotNull(BrandUseCaseConstants.BRAND_ALREADY_EXISTS_ERROR);
    }

    @Test
    void numericConstantsShouldBePositive() {
        assertTrue(BrandUseCaseConstants.BRAND_NAME_MAX_LENGTH > 0);
        assertTrue(BrandUseCaseConstants.BRAND_DESCRIPTION_MAX_LENGTH > 0);
    }

    @Test
    void descriptionMaxLengthShouldBeGreaterThanNameMaxLength() {
        assertTrue(BrandUseCaseConstants.BRAND_DESCRIPTION_MAX_LENGTH > BrandUseCaseConstants.BRAND_NAME_MAX_LENGTH);
    }
}