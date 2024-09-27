package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.controller.ArticleRestControllerAdapter;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddArticleRequest;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleResponseMapper;
import com.Emazon.Stock.domain.api.IArticleServicePort;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleRestControllerAdapterTest {

    @Mock
    private IArticleServicePort articleService;

    @Mock
    private IArticleRequestMapper articleRequestMapper;

    @Mock
    private IArticleResponseMapper articleResponseMapper;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private IBrandServicePort brandServicePort;

    @InjectMocks
    private ArticleRestControllerAdapter articleRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddArticle_successfulCreation() {
        // Arrange
        AddArticleRequest addArticleRequest = mock(AddArticleRequest.class);
        Article article = mock(Article.class);

        when(articleRequestMapper.addRequestToArticle(addArticleRequest, categoryServicePort, brandServicePort)).thenReturn(article);
        doNothing().when(articleService).saveArticle(article);

        // Act
        ResponseEntity<String> response = articleRestControllerAdapter.addArticle(addArticleRequest);

        // Assert
        verify(articleRequestMapper, times(1)).addRequestToArticle(addArticleRequest, categoryServicePort, brandServicePort);
        verify(articleService, times(1)).saveArticle(article);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Article successfully created", response.getBody());
    }

    @Test
    void testAddArticle_conflictError() {
        // Arrange
        AddArticleRequest addArticleRequest = mock(AddArticleRequest.class);
        Article article = mock(Article.class);

        when(articleRequestMapper.addRequestToArticle(addArticleRequest, categoryServicePort, brandServicePort)).thenReturn(article);
        doThrow(new RuntimeException("Conflict")).when(articleService).saveArticle(article);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            articleRestControllerAdapter.addArticle(addArticleRequest);
        });

        assertEquals("Conflict", exception.getMessage());
    }
}