package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryIsValid() {
        // Arrange
        Category validCategory = new Category(1L, "Electronics", "Devices and gadgets");

        // Mock behavior for checking unique name
        when(categoryPersistencePort.existsByName(validCategory.getNombre())).thenReturn(false);

        // Act
        assertDoesNotThrow(() -> categoryUseCase.saveCategory(validCategory));

        // Assert
        verify(categoryPersistencePort, times(1)).saveCategory(validCategory);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryIsNull() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(null));
        assertEquals("Category cannot be null", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsNull() {
        // Arrange
        Category categoryWithNullName = new Category(1L, null, "Description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithNullName));
        assertEquals("Category name cannot be null or empty", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsEmpty() {
        // Arrange
        Category categoryWithEmptyName = new Category(1L, "", "Description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithEmptyName));
        assertEquals("Category name cannot be null or empty", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionIsNull() {
        // Arrange
        Category categoryWithNullDescription = new Category(1L, "Electronics", null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithNullDescription));
        assertEquals("Category description cannot be null or empty", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionIsEmpty() {
        // Arrange
        Category categoryWithEmptyDescription = new Category(1L, "Electronics", "");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithEmptyDescription));
        assertEquals("Category description cannot be null or empty", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameExceedsMaxLength() {
        // Arrange
        String longName = "ThisCategoryNameIsDefinitelyLongerThanFiftyCharacters!";
        Category categoryWithLongName = new Category(1L, longName, "Valid description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithLongName));
        assertEquals("Category name cannot exceed 50 characters", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionExceedsMaxLength() {
        // Arrange
        String longDescription = "This description is definitely longer than ninety characters. It's far too long for our current model!!!!!";
        Category categoryWithLongDescription = new Category(1L, "Electronics", longDescription);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> categoryUseCase.saveCategory(categoryWithLongDescription));
        assertEquals("Category description cannot exceed 90 characters", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsNotUnique() {
        // Arrange
        Category categoryWithNonUniqueName = new Category(null, "Electronics", "Valid description");


        when(categoryPersistencePort.existsByName("Electronics")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithNonUniqueName));


        assertEquals("Category name must be unique", exception.getMessage());


        verify(categoryPersistencePort, times(1)).existsByName("Electronics");
    }

    public void testGetPagedCategories() {
        // Arrange: Simulamos una lista de categorías y una página
        List<Category> categoryList = Arrays.asList(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        PagedResult<Category> pagedResult = new PagedResult<>(
                categoryList, 0, 10, 2L, 1 // Parametros esperados por el constructor
        );

        // Simulamos el comportamiento del puerto de persistencia
        when(categoryPersistencePort.getPagedCategories(0, 10, true)).thenReturn(pagedResult);

        // Act: Ejecutamos el método bajo prueba
        PagedResult<Category> result = categoryUseCase.getPagedCategories(0, 10, true);

        // Assert: Comprobamos que el resultado es el esperado
        assertEquals(2, result.getContent().size());
        assertEquals("Category 1", result.getContent().get(0).getNombre());
        assertEquals(1, result.getTotalPages());

        // Verificamos que el método fue llamado una vez con los parámetros correctos
        verify(categoryPersistencePort, times(1)).getPagedCategories(0, 10, true);
    }


    @Test
    void testGetAllCategories() {
        // Arrange
        Category category1 = new Category(1L, "Category1", "Description1");
        Category category2 = new Category(2L, "Category2", "Description2");

        List<Category> categories = Arrays.asList(category1, category2);

        // Mocking behavior
        when(categoryPersistencePort.getAllCategories()).thenReturn(categories);

        // Act
        List<Category> result = categoryUseCase.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getNombre());
        assertEquals("Category2", result.get(1).getNombre());

        // Verify that the persistence port method was called
        verify(categoryPersistencePort, times(1)).getAllCategories();
    }
}