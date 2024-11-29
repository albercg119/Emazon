package com.Emazon.Stock.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrandTest {

    private Brand brand;

    @BeforeEach
    void setUp() {
        // Inicialización de un objeto Brand antes de cada prueba
        brand = new Brand(1L, "Samsung", "Fabricante de dispositivos electrónicos");
    }

    @Test
    void testBrandCreation() {
        // Verificar que el objeto Brand se creó correctamente
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
        assertEquals("Samsung", brand.getNombre());
        assertEquals("Fabricante de dispositivos electrónicos", brand.getDescripcion());
    }

    @Test
    void testSetNombre() {
        // Probar el método setNombre
        brand.setNombre("Apple");
        assertEquals("Apple", brand.getNombre());
    }

    @Test
    void testSetDescripcion() {
        // Probar el método setDescripcion
        brand.setDescripcion("Fabricante de tecnología y software");
        assertEquals("Fabricante de tecnología y software", brand.getDescripcion());
    }

    @Test
    void testSetId() {
        // Probar el método setId
        brand.setId(2L);
        assertEquals(2L, brand.getId());
    }
}