package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandControllerConstantsTest {

    @Test
    void endpoints_ShouldHaveCorrectValues() {
        assertEquals("/brand", BrandControllerConstants.BRAND_BASE_ENDPOINT);
        assertEquals("/", BrandControllerConstants.BRAND_ADD_ENDPOINT);
    }

    @Test
    void swagger_ShouldHaveCorrectValues() {
        assertEquals("Añadir una nueva marca", BrandControllerConstants.ADD_BRAND_SUMMARY);
        assertEquals("Marca creada con éxito", BrandControllerConstants.BRAND_CREATED_DESCRIPTION);
    }

    @Test
    void responseValues_ShouldHaveCorrectValues() {
        assertEquals("201", BrandControllerConstants.CREATED_RESPONSE_CODE);
        assertEquals("Brand successfully created", BrandControllerConstants.BRAND_CREATED_MESSAGE);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Endpoints
        assertNotNull(BrandControllerConstants.BRAND_BASE_ENDPOINT);
        assertNotNull(BrandControllerConstants.BRAND_ADD_ENDPOINT);

        // Swagger documentation
        assertNotNull(BrandControllerConstants.ADD_BRAND_SUMMARY);
        assertNotNull(BrandControllerConstants.BRAND_CREATED_DESCRIPTION);

        // Response values
        assertNotNull(BrandControllerConstants.CREATED_RESPONSE_CODE);
        assertNotNull(BrandControllerConstants.BRAND_CREATED_MESSAGE);
    }

    @Test
    void endpoints_ShouldStartWithSlash() {
        assertTrue(BrandControllerConstants.BRAND_BASE_ENDPOINT.startsWith("/"));
        assertTrue(BrandControllerConstants.BRAND_ADD_ENDPOINT.startsWith("/"));
    }

    @Test
    void responseCode_ShouldBeValidHttpCreatedCode() {
        assertEquals("201", BrandControllerConstants.CREATED_RESPONSE_CODE);
    }

    @Test
    void messages_ShouldNotBeEmpty() {
        assertFalse(BrandControllerConstants.ADD_BRAND_SUMMARY.isEmpty());
        assertFalse(BrandControllerConstants.BRAND_CREATED_DESCRIPTION.isEmpty());
        assertFalse(BrandControllerConstants.BRAND_CREATED_MESSAGE.isEmpty());
    }

    @Test
    void brandCreatedMessage_ShouldContainKeywords() {
        String message = BrandControllerConstants.BRAND_CREATED_MESSAGE.toLowerCase();
        assertTrue(message.contains("brand"));
        assertTrue(message.contains("success"));
    }

    @Test
    void swaggerMessages_ShouldBeInSpanish() {
        assertTrue(BrandControllerConstants.ADD_BRAND_SUMMARY.contains("marca"));
        assertTrue(BrandControllerConstants.BRAND_CREATED_DESCRIPTION.contains("éxito"));
    }
}