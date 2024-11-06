package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.ArticleResponse;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IArticleResponseMapperTest {

    private final IArticleResponseMapper mapper = Mappers.getMapper(IArticleResponseMapper.class);

    @Mock
    private Article mockArticle;

    @Mock
    private Brand mockBrand;

    @Mock
    private Category mockCategory;

    @Test
    void toArticleResponse_WhenArticleIsComplete_ShouldMapAllFields() {
        // Arrange
        when(mockBrand.getNombre()).thenReturn("Nike");
        when(mockCategory.getId()).thenReturn(1L);
        when(mockCategory.getNombre()).thenReturn("Deportes");

        when(mockArticle.getId()).thenReturn(1L);
        when(mockArticle.getName()).thenReturn("Zapatillas Running");
        when(mockArticle.getDescription()).thenReturn("Zapatillas para correr");
        when(mockArticle.getPrice()).thenReturn(99.99);
        when(mockArticle.getBrand()).thenReturn(mockBrand);
        when(mockArticle.getCategories()).thenReturn(Arrays.asList(mockCategory));

        // Act
        ArticleResponse response = mapper.toArticleResponse(mockArticle);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Zapatillas Running", response.getName());
        assertEquals("Zapatillas para correr", response.getDescription());
        assertEquals(99.99, response.getPrice());
        assertEquals("Nike", response.getBrandName());
    }

    @Test
    void toArticleResponseList_WithEmptyList_ShouldReturnEmptyList() {
        // Arrange
        List<Article> emptyList = Collections.emptyList();

        // Act
        List<ArticleResponse> responses = mapper.toArticleResponseList(emptyList);

        // Assert
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }

    @Test
    void toArticleResponseList_WithArticles_ShouldMapAllArticles() {
        // Arrange
        when(mockBrand.getNombre()).thenReturn("Nike");
        when(mockCategory.getId()).thenReturn(1L);
        when(mockCategory.getNombre()).thenReturn("Deportes");

        when(mockArticle.getId()).thenReturn(1L);
        when(mockArticle.getName()).thenReturn("Zapatillas Running");
        when(mockArticle.getDescription()).thenReturn("Zapatillas para correr");
        when(mockArticle.getPrice()).thenReturn(99.99);
        when(mockArticle.getBrand()).thenReturn(mockBrand);
        when(mockArticle.getCategories()).thenReturn(Arrays.asList(mockCategory));

        List<Article> articles = Collections.singletonList(mockArticle);

        // Act
        List<ArticleResponse> responses = mapper.toArticleResponseList(articles);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Zapatillas Running", responses.get(0).getName());
    }

    @Test
    void mapCategories_ShouldMapCategoriesCorrectly() {
        // Arrange
        when(mockCategory.getId()).thenReturn(1L);
        when(mockCategory.getNombre()).thenReturn("Deportes");

        // Act
        List<CategoryResponse> responses = mapper.mapCategories(Collections.singletonList(mockCategory));

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(1L, responses.get(0).getId());
        assertEquals("Deportes", responses.get(0).getName());
        assertNull(responses.get(0).getDescription());
    }

    @Test
    void toArticleResponsePagedResult_ShouldMapPagedResultCorrectly() {
        // Arrange
        when(mockBrand.getNombre()).thenReturn("Nike");
        when(mockCategory.getId()).thenReturn(1L);
        when(mockCategory.getNombre()).thenReturn("Deportes");

        when(mockArticle.getId()).thenReturn(1L);
        when(mockArticle.getName()).thenReturn("Zapatillas Running");
        when(mockArticle.getDescription()).thenReturn("Zapatillas para correr");
        when(mockArticle.getPrice()).thenReturn(99.99);
        when(mockArticle.getBrand()).thenReturn(mockBrand);
        when(mockArticle.getCategories()).thenReturn(Arrays.asList(mockCategory));

        List<Article> articles = Collections.singletonList(mockArticle);
        PagedResult<Article> pagedResult = new PagedResult<>(articles, 0, 10, 1L, 1);

        // Act
        PagedResult<ArticleResponse> response = mapper.toArticleResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getContent().size());
        assertEquals(0, response.getPage());
        assertEquals(10, response.getSize());
        assertEquals(1L, response.getTotalElements());
        assertEquals(1, response.getTotalPages());
    }
}