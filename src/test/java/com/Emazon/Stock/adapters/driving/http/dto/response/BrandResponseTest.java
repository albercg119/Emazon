package com.Emazon.Stock.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandResponseTest {

    @Test
    public void testBrandResponse() {
        // Arrange
        Long expectedId = 1L;
        String expectedName = "Apple";
        String expectedDescription = "Technology and devices";

        // Act
        BrandResponse brandResponse = new BrandResponse(expectedId, expectedName, expectedDescription);

        // Assert
        assertEquals(expectedId, brandResponse.getId(), "ID should be equal");
        assertEquals(expectedName, brandResponse.getName(), "Name should be equal");
        assertEquals(expectedDescription, brandResponse.getDescription(), "Description should be equal");
    }
}