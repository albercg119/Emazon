package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ICategoryRepositoryTest {

    @Autowired
    private ICategoryRepository categoryRepository;

    private CategoryEntity categoryEntity1;
    private CategoryEntity categoryEntity2;

    @BeforeEach
    void setUp() {
        // Crear dos categorías de ejemplo para los tests
        categoryEntity1 = new CategoryEntity();
        categoryEntity1.setNombre("Electronics");
        categoryEntity1.setDescripcion("Devices and gadgets");

        categoryEntity2 = new CategoryEntity();
        categoryEntity2.setNombre("Furniture");
        categoryEntity2.setDescripcion("Home and office furniture");

        // Guardar las entidades en la base de datos en memoria
        categoryRepository.save(categoryEntity1);
        categoryRepository.save(categoryEntity2);
    }

    @Test
    void testFindByNombre() {
        // Act
        Optional<CategoryEntity> result = categoryRepository.findByNombre("Electronics");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getNombre());
        assertEquals("Devices and gadgets", result.get().getDescripcion());
    }

    @Test
    void testFindByNombre_NotFound() {
        // Act
        Optional<CategoryEntity> result = categoryRepository.findByNombre("NonExistingCategory");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testExistsByNombreExcludingId_True() {
        // Act: verificar si el nombre "Furniture" existe excluyendo el ID de la categoría 1
        boolean exists = categoryRepository.existsByNombreExcludingId("Furniture", categoryEntity1.getId());

        // Assert
        assertTrue(exists);
    }

    @Test
    void testExistsByNombreExcludingId_False() {
        // Act: verificar si el nombre "Electronics" existe excluyendo su propio ID
        boolean exists = categoryRepository.existsByNombreExcludingId("Electronics", categoryEntity1.getId());

        // Assert
        assertFalse(exists);
    }

    @Test
    void testSaveCategoryWithExistingName() {
        // Arrange: crear una nueva categoría con un nombre duplicado
        CategoryEntity duplicateCategory = new CategoryEntity();
        duplicateCategory.setNombre("Electronics");
        duplicateCategory.setDescripcion("Duplicate category");

        // Act & Assert: al intentar guardar debe lanzarse una excepción de integridad
        assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(duplicateCategory));
    }
}