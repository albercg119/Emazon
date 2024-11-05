package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.utilities.Exceptions.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.PagedResult;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.utilities.constants.CategoryUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
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
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionIsNull() {
        Category categoryWithNullDescription = new Category(1L, "Electronics", null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithNullDescription));
        assertEquals(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE, exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryDescriptionIsEmpty() {
        Category categoryWithEmptyDescription = new Category(1L, "Electronics", "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> categoryUseCase.saveCategory(categoryWithEmptyDescription));
        assertEquals(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE, exception.getMessage());
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

    @Test
    void getAllCategories_ShouldReturnListOfCategories_WhenCategoriesExist() {
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Electronics", "Electronic devices"),
                new Category(2L, "Books", "Reading materials")
        );
        when(categoryPersistencePort.getAllCategories()).thenReturn(expectedCategories);

        List<Category> result = categoryUseCase.getAllCategories();

        assertEquals(expectedCategories, result);
        verify(categoryPersistencePort, times(1)).getAllCategories();
    }

    @Test
    void getAllCategories_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        when(categoryPersistencePort.getAllCategories()).thenReturn(Collections.emptyList());

        List<Category> result = categoryUseCase.getAllCategories();

        assertTrue(result.isEmpty());
        verify(categoryPersistencePort, times(1)).getAllCategories();
    }

    @Test
    void getPagedCategories_ShouldReturnPagedResult_WhenCategoriesExist() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Electronics", "Electronic devices"),
                new Category(2L, "Books", "Reading materials")
        );
        PagedResult<Category> expectedResult = new PagedResult<>(
                categories,
                0,
                10,
                2L,
                1
        );
        when(categoryPersistencePort.getPagedCategories(0, 10, true))
                .thenReturn(expectedResult);

        PagedResult<Category> result = categoryUseCase.getPagedCategories(0, 10, true);

        assertEquals(expectedResult.getContent(), result.getContent());
        assertEquals(expectedResult.getPage(), result.getPage());
        assertEquals(expectedResult.getSize(), result.getSize());
        assertEquals(expectedResult.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResult.getTotalPages(), result.getTotalPages());
        verify(categoryPersistencePort, times(1)).getPagedCategories(0, 10, true);
    }

    @Test
    void getPagedCategories_ShouldReturnEmptyPagedResult_WhenNoCategoriesExist() {
        PagedResult<Category> expectedResult = new PagedResult<>(
                new ArrayList<>(),
                0,
                10,
                0L,
                0
        );
        when(categoryPersistencePort.getPagedCategories(0, 10, true))
                .thenReturn(expectedResult);

        PagedResult<Category> result = categoryUseCase.getPagedCategories(0, 10, true);

        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals(0L, result.getTotalElements());
        assertEquals(0, result.getTotalPages());
        verify(categoryPersistencePort, times(1)).getPagedCategories(0, 10, true);
    }
}