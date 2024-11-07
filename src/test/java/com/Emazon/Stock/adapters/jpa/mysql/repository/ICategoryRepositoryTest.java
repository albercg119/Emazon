package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ICategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Test
    void findByNombre_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        CategoryEntity category = new CategoryEntity();
        category.setNombre("Deportes");
        category.setDescripcion("Artículos deportivos");
        entityManager.persist(category);
        entityManager.flush();

        // Act
        Optional<CategoryEntity> found = categoryRepository.findByNombre("Deportes");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Deportes", found.get().getNombre());
        assertEquals("Artículos deportivos", found.get().getDescripcion());
    }

    @Test
    void findByNombre_WhenCategoryDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<CategoryEntity> found = categoryRepository.findByNombre("Categoría inexistente");

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    void save_ShouldPersistCategory() {
        // Arrange
        CategoryEntity category = new CategoryEntity();
        category.setNombre("Ropa");
        category.setDescripcion("Artículos de vestir");

        // Act
        CategoryEntity saved = categoryRepository.save(category);

        // Assert
        assertNotNull(saved.getId());
        assertEquals("Ropa", saved.getNombre());
        assertEquals("Artículos de vestir", saved.getDescripcion());
    }

    @Test
    void findById_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        CategoryEntity category = new CategoryEntity();
        category.setNombre("Electrónicos");
        category.setDescripcion("Artículos electrónicos");
        entityManager.persist(category);
        entityManager.flush();

        // Act
        Optional<CategoryEntity> found = categoryRepository.findById(category.getId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Electrónicos", found.get().getNombre());
    }

    @Test
    void findById_WhenCategoryDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<CategoryEntity> found = categoryRepository.findById(999L);

        // Assert
        assertTrue(found.isEmpty());
    }
}