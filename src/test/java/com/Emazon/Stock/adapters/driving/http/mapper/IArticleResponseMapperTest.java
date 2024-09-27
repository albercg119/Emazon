package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.ArticleResponse;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.model.Category;
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

class IArticleResponseMapperTest {

    @InjectMocks
    private IArticleResponseMapper articleResponseMapper;

    @Mock
    private Article article;

    @Mock
    private Brand brand;

    @Mock
    private Category category;

    @Mock
    private PagedResult<Article> pagedResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToArticleResponse() {
        // Simulamos un artículo con categorías y marca
        when(article.getId()).thenReturn(1L);
        when(article.getName()).thenReturn("Laptop");
        when(article.getDescription()).thenReturn("Laptop con 16GB RAM");
        when(article.getPrice()).thenReturn(1500.0);

        when(brand.getNombre()).thenReturn("HP");
        when(article.getBrand()).thenReturn(brand);

        when(category.getId()).thenReturn(1L);
        when(category.getNombre()).thenReturn("Electrónica");
        List<Category> categories = Arrays.asList(category);
        when(article.getCategories()).thenReturn(categories);

        // Llamamos al método de mapeo
        ArticleResponse articleResponse = articleResponseMapper.toArticleResponse(article);

        // Validamos el mapeo
        assertNotNull(articleResponse);
        assertEquals(1L, articleResponse.getId());
        assertEquals("Laptop", articleResponse.getName());
        assertEquals("Laptop con 16GB RAM", articleResponse.getDescription());
        assertEquals(1500.0, articleResponse.getPrice());
        assertEquals("HP", articleResponse.getBrandName());
        assertNotNull(articleResponse.getCategories());
        assertEquals(1, articleResponse.getCategories().size());
        assertEquals("Electrónica", articleResponse.getCategories().get(0).getName());
        assertNull(articleResponse.getCategories().get(0).getDescription()); // Validamos que la descripción sea null
    }

    @Test
    void testToArticleResponseList() {
        // Simulamos una lista de artículos
        Article article1 = mock(Article.class);
        Article article2 = mock(Article.class);

        when(article1.getId()).thenReturn(1L);
        when(article1.getName()).thenReturn("Laptop");
        when(article1.getDescription()).thenReturn("Laptop con 16GB RAM");
        when(article1.getPrice()).thenReturn(1500.0);
        when(brand.getNombre()).thenReturn("HP");
        when(article1.getBrand()).thenReturn(brand);

        when(article2.getId()).thenReturn(2L);
        when(article2.getName()).thenReturn("Smartphone");
        when(article2.getDescription()).thenReturn("Smartphone con cámara 4K");
        when(article2.getPrice()).thenReturn(800.0);
        when(brand.getNombre()).thenReturn("Samsung");
        when(article2.getBrand()).thenReturn(brand);

        List<Article> articles = Arrays.asList(article1, article2);

        // Llamamos al método de mapeo
        List<ArticleResponse> articleResponseList = articleResponseMapper.toArticleResponseList(articles);

        // Validamos el mapeo
        assertNotNull(articleResponseList);
        assertEquals(2, articleResponseList.size());
        assertEquals("Laptop", articleResponseList.get(0).getName());
        assertEquals("Smartphone", articleResponseList.get(1).getName());
    }

    @Test
    void testToArticleResponsePagedResult() {
        // Simulamos un PagedResult con una lista de artículos
        Article article1 = mock(Article.class);
        Article article2 = mock(Article.class);

        when(article1.getId()).thenReturn(1L);
        when(article1.getName()).thenReturn("Laptop");
        when(article1.getDescription()).thenReturn("Laptop con 16GB RAM");
        when(article1.getPrice()).thenReturn(1500.0);

        when(article2.getId()).thenReturn(2L);
        when(article2.getName()).thenReturn("Smartphone");
        when(article2.getDescription()).thenReturn("Smartphone con cámara 4K");
        when(article2.getPrice()).thenReturn(800.0);

        List<Article> articles = Arrays.asList(article1, article2);

        when(pagedResult.getContent()).thenReturn(articles);
        when(pagedResult.getPage()).thenReturn(1);
        when(pagedResult.getSize()).thenReturn(10);
        when(pagedResult.getTotalElements()).thenReturn(2L);
        when(pagedResult.getTotalPages()).thenReturn(1);

        // Llamamos al método de mapeo
        PagedResult<ArticleResponse> articleResponsePagedResult = articleResponseMapper.toArticleResponsePagedResult(pagedResult);

        // Validamos el mapeo
        assertNotNull(articleResponsePagedResult);
        assertEquals(2, articleResponsePagedResult.getContent().size());
        assertEquals(1, articleResponsePagedResult.getPage());
        assertEquals(10, articleResponsePagedResult.getSize());
        assertEquals(2L, articleResponsePagedResult.getTotalElements());
        assertEquals(1, articleResponsePagedResult.getTotalPages());
    }
}