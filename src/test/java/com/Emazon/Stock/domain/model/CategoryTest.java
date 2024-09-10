package com.Emazon.Stock.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        // Inicialización de un objeto Category antes de cada prueba
        category = new Category(1L, "Electrónica", "Artículos relacionados con dispositivos electrónicos");
    }

    @Test
    void testCategoryCreation() {
        // Verificar que el objeto Category se creó correctamente
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Electrónica", category.getNombre());
        assertEquals("Artículos relacionados con dispositivos electrónicos", category.getDescripcion());
    }

    @Test
    void testSetNombre() {
        // Probar el método setNombre
        category.setNombre("Ropa");
        assertEquals("Ropa", category.getNombre());
    }

    @Test
    void testSetDescripcion() {
        // Probar el método setDescripcion
        category.setDescripcion("Artículos de vestimenta");
        assertEquals("Artículos de vestimenta", category.getDescripcion());
    }

    @Test
    void testSetId() {
        // Probar el método setId
        category.setId(2L);
        assertEquals(2L, category.getId());
    }
}