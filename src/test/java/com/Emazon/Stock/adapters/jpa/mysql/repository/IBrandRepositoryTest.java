package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IBrandRepositoryTest {

    @Autowired
    private IBrandRepository brandRepository;

    private BrandEntity brandEntity1;
    private BrandEntity brandEntity2;

    @BeforeEach
    void setUp() {
        // Crear dos marcas de ejemplo para los tests
        brandEntity1 = new BrandEntity();
        brandEntity1.setNombre("Samsung");
        brandEntity1.setDescripcion("Electronics and home appliances");

        brandEntity2 = new BrandEntity();
        brandEntity2.setNombre("Apple");
        brandEntity2.setDescripcion("Technology and software");

        // Guardar las entidades en la base de datos en memoria
        brandRepository.save(brandEntity1);
        brandRepository.save(brandEntity2);
    }

    @Test
    void testFindByNombre() {
        // Act
        Optional<BrandEntity> result = brandRepository.findByNombre("Samsung");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Samsung", result.get().getNombre());
        assertEquals("Electronics and home appliances", result.get().getDescripcion());
    }

    @Test
    void testFindByNombre_NotFound() {
        // Act
        Optional<BrandEntity> result = brandRepository.findByNombre("NonExistingBrand");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testExistsByNombreExcludingId_True() {
        // Act: verificar si el nombre "Apple" existe excluyendo el ID de la marca 1
        boolean exists = brandRepository.existsByNombreExcludingId("Apple", brandEntity1.getId());

        // Assert
        assertTrue(exists);
    }

    @Test
    void testExistsByNombreExcludingId_False() {
        // Act: verificar si el nombre "Samsung" existe excluyendo su propio ID
        boolean exists = brandRepository.existsByNombreExcludingId("Samsung", brandEntity1.getId());

        // Assert
        assertFalse(exists);
    }
}