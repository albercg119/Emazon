package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandEntityConstantsTest {

    @Test
    void tableAndColumnNames_ShouldHaveCorrectValues() {
        assertEquals("brand", BrandEntityConstants.TABLE_NAME);
        assertEquals("id", BrandEntityConstants.ID_COLUMN);
        assertEquals("nombre", BrandEntityConstants.NOMBRE_COLUMN);
        assertEquals("descripcion", BrandEntityConstants.DESCRIPCION_COLUMN);
    }

    @Test
    void generationStrategy_ShouldHaveCorrectValue() {
        assertEquals("IDENTITY", BrandEntityConstants.GENERATION_STRATEGY);
    }

    @Test
    void columnLengths_ShouldHaveCorrectValues() {
        assertEquals(50, BrandEntityConstants.NOMBRE_MAX_LENGTH);
        assertEquals(120, BrandEntityConstants.DESCRIPCION_MAX_LENGTH);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Table and column names
        assertNotNull(BrandEntityConstants.TABLE_NAME);
        assertNotNull(BrandEntityConstants.ID_COLUMN);
        assertNotNull(BrandEntityConstants.NOMBRE_COLUMN);
        assertNotNull(BrandEntityConstants.DESCRIPCION_COLUMN);

        // Generation strategy
        assertNotNull(BrandEntityConstants.GENERATION_STRATEGY);
    }

    @Test
    void maxLengthValues_ShouldBePositive() {
        assertTrue(BrandEntityConstants.NOMBRE_MAX_LENGTH > 0);
        assertTrue(BrandEntityConstants.DESCRIPCION_MAX_LENGTH > 0);
    }

    @Test
    void descripcionMaxLength_ShouldBeGreaterThanNombreMaxLength() {
        assertTrue(BrandEntityConstants.DESCRIPCION_MAX_LENGTH > BrandEntityConstants.NOMBRE_MAX_LENGTH);
    }

    @Test
    void columnNames_ShouldBeLowerCase() {
        assertEquals(BrandEntityConstants.TABLE_NAME.toLowerCase(), BrandEntityConstants.TABLE_NAME);
        assertEquals(BrandEntityConstants.ID_COLUMN.toLowerCase(), BrandEntityConstants.ID_COLUMN);
        assertEquals(BrandEntityConstants.NOMBRE_COLUMN.toLowerCase(), BrandEntityConstants.NOMBRE_COLUMN);
        assertEquals(BrandEntityConstants.DESCRIPCION_COLUMN.toLowerCase(), BrandEntityConstants.DESCRIPCION_COLUMN);
    }

    @Test
    void generationStrategy_ShouldBeUpperCase() {
        assertEquals(BrandEntityConstants.GENERATION_STRATEGY.toUpperCase(), BrandEntityConstants.GENERATION_STRATEGY);
    }

    @Test
    void columnNames_ShouldNotContainSpaces() {
        assertFalse(BrandEntityConstants.TABLE_NAME.contains(" "));
        assertFalse(BrandEntityConstants.ID_COLUMN.contains(" "));
        assertFalse(BrandEntityConstants.NOMBRE_COLUMN.contains(" "));
        assertFalse(BrandEntityConstants.DESCRIPCION_COLUMN.contains(" "));
    }

    @Test
    void maxLengths_ShouldFollowDatabaseBestPractices() {
        // Verificar que las longitudes máximas sean múltiplos comunes en bases de datos
        assertTrue(BrandEntityConstants.NOMBRE_MAX_LENGTH <= 50);
        assertTrue(BrandEntityConstants.DESCRIPCION_MAX_LENGTH <= 255);
    }
}