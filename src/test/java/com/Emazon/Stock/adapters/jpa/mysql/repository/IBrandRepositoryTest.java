package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IBrandRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IBrandRepository brandRepository;

    @Test
    void findByNombre_WhenBrandExists_ShouldReturnBrand() {
        // Arrange
        BrandEntity brand = new BrandEntity();
        brand.setNombre("Nike");
        brand.setDescripcion("Marca deportiva");
        entityManager.persist(brand);
        entityManager.flush();

        // Act
        Optional<BrandEntity> found = brandRepository.findByNombre("Nike");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Nike", found.get().getNombre());
        assertEquals("Marca deportiva", found.get().getDescripcion());
    }

    @Test
    void findByNombre_WhenBrandDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<BrandEntity> found = brandRepository.findByNombre("Marca inexistente");

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    void save_ShouldPersistBrand() {
        // Arrange
        BrandEntity brand = new BrandEntity();
        brand.setNombre("Adidas");
        brand.setDescripcion("Otra marca deportiva");

        // Act
        BrandEntity saved = brandRepository.save(brand);

        // Assert
        assertNotNull(saved.getId());
        assertEquals("Adidas", saved.getNombre());
        assertEquals("Otra marca deportiva", saved.getDescripcion());
    }

    @Test
    void findById_WhenBrandExists_ShouldReturnBrand() {
        // Arrange
        BrandEntity brand = new BrandEntity();
        brand.setNombre("Puma");
        brand.setDescripcion("Marca deportiva");
        entityManager.persist(brand);
        entityManager.flush();

        // Act
        Optional<BrandEntity> found = brandRepository.findById(brand.getId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Puma", found.get().getNombre());
    }

    @Test
    void findById_WhenBrandDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<BrandEntity> found = brandRepository.findById(999L);

        // Assert
        assertTrue(found.isEmpty());
    }
}