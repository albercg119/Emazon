package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.utilities.exception.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.utilities.constants.CategoryUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        Category validCategory = new Category(1L, "Electronics", "Devices and gadgets");
        when(categoryPersistencePort.existsByName(validCategory.getNombre())).thenReturn(false);
        assertDoesNotThrow(() -> categoryUseCase.saveCategory(validCategory));
        verify(categoryPersistencePort, times(1)).saveCategory(validCategory);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(null));
        assertEquals(CategoryUseCaseConstants.CATEGORY_NULL_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsNull() {
        Category categoryWithNullName = new Category(1L, null, "Description");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithNullName));
        assertEquals(CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsEmpty() {
        Category categoryWithEmptyName = new Category(1L, "", "Description");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithEmptyName));
        assertEquals(CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldSetDefaultDescription_WhenCategoryDescriptionIsNull() {
        Category categoryWithNullDescription = new Category(1L, "Electronics", null);
        when(categoryPersistencePort.existsByName("Electronics")).thenReturn(false);

        categoryUseCase.saveCategory(categoryWithNullDescription);

        assertEquals(CategoryUseCaseConstants.DEFAULT_CATEGORY_DESCRIPTION, categoryWithNullDescription.getDescripcion());
        verify(categoryPersistencePort).saveCategory(categoryWithNullDescription);
    }

    @Test
    void saveCategory_ShouldSetDefaultDescription_WhenCategoryDescriptionIsEmpty() {
        Category categoryWithEmptyDescription = new Category(1L, "Electronics", "");
        when(categoryPersistencePort.existsByName("Electronics")).thenReturn(false);

        categoryUseCase.saveCategory(categoryWithEmptyDescription);

        assertEquals(CategoryUseCaseConstants.DEFAULT_CATEGORY_DESCRIPTION, categoryWithEmptyDescription.getDescripcion());
        verify(categoryPersistencePort).saveCategory(categoryWithEmptyDescription);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameExceedsMaxLength() {
        String longName = "ThisCategoryNameIsDefinitelyLongerThanFiftyCharacters!";
        Category categoryWithLongName = new Category(1L, longName, "Valid description");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithLongName));
        assertEquals(CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionExceedsMaxLength() {
        String longDescription = "This description is definitely longer than ninety characters. It's far too long for our current model!!!!!";
        Category categoryWithLongDescription = new Category(1L, "Electronics", longDescription);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithLongDescription));
        assertEquals(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryNameIsNotUnique() {
        Category categoryWithNonUniqueName = new Category(null, "Electronics", "Valid description");
        when(categoryPersistencePort.existsByName("Electronics")).thenReturn(true);

        CategoryAlreadyExistsDomainException exception = assertThrows(
                CategoryAlreadyExistsDomainException.class,
                () -> categoryUseCase.saveCategory(categoryWithNonUniqueName)
        );

        assertEquals(CategoryUseCaseConstants.CATEGORY_ALREADY_EXISTS_MESSAGE, exception.getMessage());
        verify(categoryPersistencePort, times(1)).existsByName("Electronics");
    }
}