package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryEntityTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        Long expectedId = 1L;
        String expectedNombre = "Electronics";
        String expectedDescripcion = "Devices and gadgets";

        // Act
        categoryEntity.setId(expectedId);
        categoryEntity.setNombre(expectedNombre);
        categoryEntity.setDescripcion(expectedDescripcion);

        // Assert
        assertEquals(expectedId, categoryEntity.getId());
        assertEquals(expectedNombre, categoryEntity.getNombre());
        assertEquals(expectedDescripcion, categoryEntity.getDescripcion());
    }

    @Test
    void testSetId() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        Long expectedId = 2L;

        // Act
        categoryEntity.setId(expectedId);

        // Assert
        assertEquals(expectedId, categoryEntity.getId());
    }

    @Test
    void testSetNombre() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        String expectedNombre = "Fashion";

        // Act
        categoryEntity.setNombre(expectedNombre);

        // Assert
        assertEquals(expectedNombre, categoryEntity.getNombre());
    }

    @Test
    void testSetDescripcion() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        String expectedDescripcion = "Clothing and accessories";

        // Act
        categoryEntity.setDescripcion(expectedDescripcion);

        // Assert
        assertEquals(expectedDescripcion, categoryEntity.getDescripcion());
    }
}