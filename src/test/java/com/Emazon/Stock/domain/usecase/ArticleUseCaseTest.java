package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.constants.ArticleUseCaseConstants;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IArticlePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;


    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_ShouldSaveArticle_WhenArticleIsValid() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        Article validArticle = new Article(1L, "Laptop", "High-performance laptop", 10, 1500.0, brand, List.of(
                new Category(1L, "Electronics", "Devices"),
                new Category(2L, "Computers", "Laptops")
        ));

        // Mock behavior for checking unique name
        when(articlePersistencePort.existsByName(validArticle.getName())).thenReturn(false);

        // Act
        assertDoesNotThrow(() -> articleUseCase.saveArticle(validArticle));

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(validArticle);
    }

    @Test
    void saveArticle_ShouldThrowException_WhenArticleNameIsNotUnique() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        Article articleWithNonUniqueName = new Article(null, "Laptop", "Valid description", 10, 1500.0, brand, List.of(
                new Category(1L, "Electronics", "Devices")
        ));

        when(articlePersistencePort.existsByName("Laptop")).thenReturn(true);

        // Act & Assert
        ArticleAlreadyExistsDomainException exception = assertThrows(ArticleAlreadyExistsDomainException.class,
                () -> articleUseCase.saveArticle(articleWithNonUniqueName));

        assertEquals(ArticleUseCaseConstants.ARTICLE_NAME_UNIQUE_MESSAGE, exception.getMessage());

        verify(articlePersistencePort, times(1)).existsByName("Laptop");
    }

    @Test
    void saveArticle_ShouldThrowException_WhenArticleHasEmptyFields() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        Article invalidArticle = new Article(1L, "", "", 0, 0.0, brand, List.of());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(invalidArticle));
        assertEquals(ArticleUseCaseConstants.EMPTY_FIELDS_MESSAGE, exception.getMessage());
    }

    @Test
    void saveArticle_ShouldThrowException_WhenCategoriesAreDuplicate() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices");
        Article articleWithDuplicateCategories = new Article(1L, "Laptop", "Valid description", 10, 1500.0,
                new Brand(1L, "BrandName", "BrandDescription"),
                List.of(category, category)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articleWithDuplicateCategories));

        assertEquals(ArticleUseCaseConstants.DUPLICATE_CATEGORIES_MESSAGE, exception.getMessage());
    }

    @Test
    void saveArticle_ShouldThrowException_WhenCategoryCountIsMoreThanThree() {
        // Arrange
        Article articleWithTooManyCategories = new Article(1L, "Laptop", "Valid description", 10, 1500.0,
                new Brand(1L, "BrandName", "BrandDescription"),
                List.of(
                        new Category(1L, "Electronics", "Devices"),
                        new Category(2L, "Computers", "Laptops"),
                        new Category(3L, "Gadgets", "Various gadgets"),
                        new Category(4L, "Office", "Office equipment")
                )
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articleWithTooManyCategories));

        assertEquals(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE, exception.getMessage());
    }

    @Test
    void saveArticle_ShouldThrowException_WhenCategoryCountIsZero() {
        // Arrange: Artículo con todos los campos válidos excepto las categorías vacías
        Article articleWithNoCategories = new Article(1L, "Laptop", "Valid description", 10, 1500.0,
                new Brand(1L, "BrandName", "BrandDescription"),
                List.of() // Lista de categorías vacía
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articleWithNoCategories));

        // Asegurarse de que la excepción lanzada es por el número de categorías
        assertEquals(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE, exception.getMessage());
    }
}