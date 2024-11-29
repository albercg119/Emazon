package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandEntityTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        Long expectedId = 1L;
        String expectedNombre = "Apple";
        String expectedDescripcion = "Technology and devices";

        // Act
        brandEntity.setId(expectedId);
        brandEntity.setNombre(expectedNombre);
        brandEntity.setDescripcion(expectedDescripcion);

        // Assert
        assertEquals(expectedId, brandEntity.getId());
        assertEquals(expectedNombre, brandEntity.getNombre());
        assertEquals(expectedDescripcion, brandEntity.getDescripcion());
    }

    @Test
    void testSetId() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        Long expectedId = 2L;

        // Act
        brandEntity.setId(expectedId);

        // Assert
        assertEquals(expectedId, brandEntity.getId());
    }

    @Test
    void testSetNombre() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        String expectedNombre = "Samsung";

        // Act
        brandEntity.setNombre(expectedNombre);

        // Assert
        assertEquals(expectedNombre, brandEntity.getNombre());
    }

    @Test
    void testSetDescripcion() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        String expectedDescripcion = "Electronics and home appliances";

        // Act
        brandEntity.setDescripcion(expectedDescripcion);

        // Assert
        assertEquals(expectedDescripcion, brandEntity.getDescripcion());
    }
}