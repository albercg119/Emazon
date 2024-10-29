package com.Emazon.Stock.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {

        category = new Category(1L, "Electrónica", "Artículos relacionados con dispositivos electrónicos");
    }

    @Test
    void testCategoryCreation() {

        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Electrónica", category.getNombre());
        assertEquals("Artículos relacionados con dispositivos electrónicos", category.getDescripcion());
    }

    @Test
    void testSetNombre() {

        category.setNombre("Ropa");
        assertEquals("Ropa", category.getNombre());
    }

    @Test
    void testSetDescripcion() {

        category.setDescripcion("Artículos de vestimenta");
        assertEquals("Artículos de vestimenta", category.getDescripcion());
    }

    @Test
    void testSetId() {

        category.setId(2L);
        assertEquals(2L, category.getId());
    }
}