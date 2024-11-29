package com.Emazon.Stock.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryResponseTest {

    @Test
    public void testCategoryResponse() {
        // Arrange
        Long expectedId = 1L;
        String expectedName = "Electronics";
        String expectedDescription = "Devices and gadgets";

        // Act
        CategoryResponse categoryResponse = new CategoryResponse(expectedId, expectedName, expectedDescription);

        // Assert
        assertEquals(expectedId, categoryResponse.getId(), "ID should be equal");
        assertEquals(expectedName, categoryResponse.getName(), "Name should be equal");
        assertEquals(expectedDescription, categoryResponse.getDescription(), "Description should be equal");
    }
}