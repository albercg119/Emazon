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

        brandEntity1 = new BrandEntity();
        brandEntity1.setNombre("Samsung");
        brandEntity1.setDescripcion("Electronics and home appliances");

        brandEntity2 = new BrandEntity();
        brandEntity2.setNombre("Apple");
        brandEntity2.setDescripcion("Technology and software");


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
    void testSaveBrandWithExistingName() {
        // Arrange: crear una nueva marca con un nombre duplicado
        BrandEntity duplicateBrand = new BrandEntity();
        duplicateBrand.setNombre("Samsung");
        duplicateBrand.setDescripcion("Duplicate brand");

        // Act & Assert: al intentar guardar debe lanzarse una excepción de integridad
        assertThrows(DataIntegrityViolationException.class, () -> brandRepository.save(duplicateBrand));
    }
}