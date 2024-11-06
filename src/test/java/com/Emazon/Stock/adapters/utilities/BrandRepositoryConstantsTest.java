package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandRepositoryConstantsTest {

    @Test
    void parameterNames_ShouldHaveCorrectValues() {
        assertEquals("nombre", BrandRepositoryConstants.PARAM_NOMBRE);
        assertEquals("id", BrandRepositoryConstants.PARAM_ID);
    }

    @Test
    void entityAlias_ShouldHaveCorrectValue() {
        assertEquals("b", BrandRepositoryConstants.ENTITY_ALIAS);
    }

    @Test
    void fieldNames_ShouldHaveCorrectValues() {
        assertEquals("nombre", BrandRepositoryConstants.NOMBRE_FIELD);
        assertEquals("id", BrandRepositoryConstants.ID_FIELD);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Query
        assertNotNull(BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID);

        // Parameters
        assertNotNull(BrandRepositoryConstants.PARAM_NOMBRE);
        assertNotNull(BrandRepositoryConstants.PARAM_ID);

        // Entity alias
        assertNotNull(BrandRepositoryConstants.ENTITY_ALIAS);

        // Field names
        assertNotNull(BrandRepositoryConstants.NOMBRE_FIELD);
        assertNotNull(BrandRepositoryConstants.ID_FIELD);
    }

    @Test
    void queryString_ShouldContainRequiredElements() {
        String query = BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID;

        // Verify basic JPQL structure
        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("FROM"));
        assertTrue(query.contains("WHERE"));

        // Verify entity alias usage
        assertTrue(query.contains(BrandRepositoryConstants.ENTITY_ALIAS + "."));

        // Verify parameter usage
        assertTrue(query.contains(":" + BrandRepositoryConstants.PARAM_NOMBRE));
        assertTrue(query.contains(":" + BrandRepositoryConstants.PARAM_ID));
    }

    @Test
    void queryString_ShouldContainCaseInsensitiveComparison() {
        String query = BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID;
        assertTrue(query.contains("LOWER("));
        assertTrue(query.contains("LOWER(:nombre)"));
    }

    @Test
    void queryString_ShouldReturnBooleanResult() {
        String query = BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID;
        assertTrue(query.contains("CASE WHEN COUNT("));
        assertTrue(query.contains("THEN true ELSE false END"));
    }

    @Test
    void entityAlias_ShouldBeSingleCharacter() {
        assertEquals(1, BrandRepositoryConstants.ENTITY_ALIAS.length());
    }

    @Test
    void fieldNames_ShouldBeLowerCase() {
        assertEquals(BrandRepositoryConstants.NOMBRE_FIELD.toLowerCase(), BrandRepositoryConstants.NOMBRE_FIELD);
        assertEquals(BrandRepositoryConstants.ID_FIELD.toLowerCase(), BrandRepositoryConstants.ID_FIELD);
    }

    @Test
    void parameterNames_ShouldMatchFieldNames() {
        assertEquals(BrandRepositoryConstants.NOMBRE_FIELD, BrandRepositoryConstants.PARAM_NOMBRE);
        assertEquals(BrandRepositoryConstants.ID_FIELD, BrandRepositoryConstants.PARAM_ID);
    }

    @Test
    void queryString_ShouldBeWellFormed() {
        String query = BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID;
        // Verify basic query structure
        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("FROM BrandEntity"));
        assertTrue(query.contains("WHERE"));
        assertTrue(query.contains("AND"));

        // Verify correct parameter usage
        assertTrue(query.contains(":" + BrandRepositoryConstants.PARAM_NOMBRE));
        assertTrue(query.contains(":" + BrandRepositoryConstants.PARAM_ID));

        // Verify logical operators
        assertTrue(query.contains("!="));

        // Verify case insensitive comparison
        assertTrue(query.contains("LOWER("));
    }
}