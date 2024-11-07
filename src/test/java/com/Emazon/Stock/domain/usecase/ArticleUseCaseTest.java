package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.PagedResult;
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

    private Brand testBrand;
    private Article testArticle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBrand = new Brand(1L, "NombreMarca", "DescripcionMarca");
        testArticle = new Article(1L, "Laptop", "Descripción válida", 10, 1500.0, testBrand,
                List.of(new Category(1L, "Electrónicos", "Dispositivos")));
    }

    // ... [Mantener los tests existentes] ...

    @Test
    void getPagedArticles_DebeRetornarResultadosPaginados() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "name";
        boolean ascending = true;

        List<Article> articles = List.of(
                testArticle,
                new Article(2L, "Monitor", "Monitor HD", 5, 300.0, testBrand,
                        List.of(new Category(1L, "Electrónicos", "Dispositivos")))
        );
        PagedResult<Article> expectedResult = new PagedResult<>(articles, page, size, 2L, 1);

        when(articlePersistencePort.getPagedArticles(page, size, sortBy, ascending))
                .thenReturn(expectedResult);

        // Act
        PagedResult<Article> result = articleUseCase.getPagedArticles(page, size, sortBy, ascending);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getContent().size(), result.getContent().size());
        assertEquals(expectedResult.getPage(), result.getPage());
        assertEquals(expectedResult.getSize(), result.getSize());
        assertEquals(expectedResult.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResult.getTotalPages(), result.getTotalPages());
        verify(articlePersistencePort).getPagedArticles(page, size, sortBy, ascending);
    }

    @Test
    void getPagedArticles_DebeRetornarListaVacia_CuandoNoHayResultados() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "name";
        boolean ascending = true;

        PagedResult<Article> expectedResult = new PagedResult<>(List.of(), page, size, 0L, 0);

        when(articlePersistencePort.getPagedArticles(page, size, sortBy, ascending))
                .thenReturn(expectedResult);

        // Act
        PagedResult<Article> result = articleUseCase.getPagedArticles(page, size, sortBy, ascending);

        // Assert
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getTotalPages());
        verify(articlePersistencePort).getPagedArticles(page, size, sortBy, ascending);
    }

    @Test
    void getPagedArticles_DebeOrdenarDescendente_CuandoAscendingEsFalse() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "price";
        boolean ascending = false;

        List<Article> articles = List.of(
                new Article(2L, "Monitor Caro", "Monitor HD", 5, 500.0, testBrand,
                        List.of(new Category(1L, "Electrónicos", "Dispositivos"))),
                new Article(1L, "Monitor Barato", "Monitor HD", 5, 300.0, testBrand,
                        List.of(new Category(1L, "Electrónicos", "Dispositivos")))
        );
        PagedResult<Article> expectedResult = new PagedResult<>(articles, page, size, 2L, 1);

        when(articlePersistencePort.getPagedArticles(page, size, sortBy, ascending))
                .thenReturn(expectedResult);

        // Act
        PagedResult<Article> result = articleUseCase.getPagedArticles(page, size, sortBy, ascending);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertTrue(result.getContent().get(0).getPrice() > result.getContent().get(1).getPrice());
        verify(articlePersistencePort).getPagedArticles(page, size, sortBy, ascending);
    }

    @Test
    void getPagedArticles_DebeManejarPaginacionCorrecta() {
        // Arrange
        int page = 1;
        int size = 2;
        String sortBy = "name";
        boolean ascending = true;

        List<Article> articles = List.of(
                new Article(3L, "Teclado", "Teclado mecánico", 8, 100.0, testBrand,
                        List.of(new Category(1L, "Electrónicos", "Dispositivos")))
        );
        PagedResult<Article> expectedResult = new PagedResult<>(articles, page, size, 3L, 2);

        when(articlePersistencePort.getPagedArticles(page, size, sortBy, ascending))
                .thenReturn(expectedResult);

        // Act
        PagedResult<Article> result = articleUseCase.getPagedArticles(page, size, sortBy, ascending);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(3L, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
        verify(articlePersistencePort).getPagedArticles(page, size, sortBy, ascending);
    }
}