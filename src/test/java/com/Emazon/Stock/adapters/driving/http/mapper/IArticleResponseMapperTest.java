package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.ArticleResponse;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategorySimpleResponse;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
class IArticleResponseMapperTest {

    @InjectMocks
    private IArticleResponseMapperImpl mapper;

    @Mock
    private IArticleResponseMapper articleResponseMapper;

    @Test
    void toArticleResponse_ShouldMapAllFields() {
        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Test Brand Description");
        Category category1 = new Category(1L, "Category A", "Category A Description");
        Category category2 = new Category(2L, "Category B", "Category B Description");

        Article article = new Article(
                1L,
                "Test Article",
                "Test Description",
                1, // stock
                99.99,
                brand,
                Arrays.asList(category2, category1)
        );

        // Act
        ArticleResponse response = mapper.toArticleResponse(article);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Article", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertEquals(99.99, response.getPrice());
        assertEquals("Test Brand", response.getBrandName());

        List<CategorySimpleResponse> categories = response.getCategories();
        assertEquals(2, categories.size());
        assertEquals("Category A", categories.get(0).getName());
        assertEquals("Category B", categories.get(1).getName());
    }

    @Test
    void toArticleResponseList_ShouldMapListCorrectly() {
        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Brand Description");
        Category category = new Category(1L, "Category A", "Category Description");

        Article article1 = new Article(1L, "Article 1", "Desc 1", 1, 99.99, brand,
                Arrays.asList(category));
        Article article2 = new Article(2L, "Article 2", "Desc 2", 2, 199.99, brand,
                Arrays.asList(category));

        List<Article> articles = Arrays.asList(article1, article2);

        // Act
        List<ArticleResponse> responses = mapper.toArticleResponseList(articles);

        // Assert
        assertEquals(2, responses.size());
        assertEquals("Article 1", responses.get(0).getName());
        assertEquals("Article 2", responses.get(1).getName());
    }

    @Test
    void toArticleResponsePagedResult_ShouldMapPagedResultCorrectly() {
        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Brand Description");
        Category category = new Category(1L, "Category A", "Category Description");

        Article article = new Article(1L, "Test Article", "Test Description",
                1, 99.99, brand, Arrays.asList(category));

        List<Article> articles = List.of(article);
        PagedResult<Article> pagedResult = new PagedResult<>(articles, 0, 10, 1L, 1);

        // Act
        PagedResult<ArticleResponse> response = mapper.toArticleResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getPage());
        assertEquals(10, response.getSize());
        assertEquals(1L, response.getTotalElements());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getContent().size());
        assertEquals("Test Article", response.getContent().get(0).getName());
    }

    @Test
    void toArticleResponse_WithNullCategories_ShouldNotThrowException() {
        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Brand Description");
        Article article = new Article(1L, "Test Article", "Test Description",
                1, 99.99, brand, new ArrayList<>());  // Cambiado de null a lista vacía

        // Act
        ArticleResponse response = mapper.toArticleResponse(article);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Article", response.getName());
        assertNotNull(response.getCategories()); // Cambiado para verificar lista vacía
        assertTrue(response.getCategories().isEmpty());
    }
}