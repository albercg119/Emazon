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
    void saveArticle_DebeGuardarArticulo_CuandoArticuloEsValido() {
        // Arrange
        Brand marca = new Brand(1L, "NombreMarca", "DescripcionMarca");
        Article articuloValido = new Article(1L, "Laptop", "Laptop de alto rendimiento", 10, 1500.0, marca, List.of(
                new Category(1L, "Electrónicos", "Dispositivos"),
                new Category(2L, "Computadoras", "Laptops")
        ));

        when(articlePersistencePort.existsByName(articuloValido.getName())).thenReturn(false);

        // Act
        assertDoesNotThrow(() -> articleUseCase.saveArticle(articuloValido));

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(articuloValido);
    }

    @Test
    void saveArticle_DebeLanzarExcepcion_CuandoNombreNoEsUnico() {
        // Arrange
        Brand marca = new Brand(1L, "NombreMarca", "DescripcionMarca");
        Article articuloConNombreDuplicado = new Article(null, "Laptop", "Descripción válida", 10, 1500.0, marca, List.of(
                new Category(1L, "Electrónicos", "Dispositivos")
        ));

        when(articlePersistencePort.existsByName("Laptop")).thenReturn(true);

        // Act & Assert
        ArticleAlreadyExistsDomainException excepcion = assertThrows(ArticleAlreadyExistsDomainException.class,
                () -> articleUseCase.saveArticle(articuloConNombreDuplicado));

        assertEquals(ArticleUseCaseConstants.ARTICLE_NAME_UNIQUE_MESSAGE, excepcion.getMessage());
    }

    @Test
    void saveArticle_DebeLanzarExcepcion_CuandoCamposEstanVacios() {
        // Arrange
        Brand marca = new Brand(1L, "NombreMarca", "DescripcionMarca");
        Article articuloInvalido = new Article(1L, "", "", 0, 0.0, marca, List.of());

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articuloInvalido));
        assertEquals(ArticleUseCaseConstants.EMPTY_FIELDS_MESSAGE, excepcion.getMessage());
    }

    @Test
    void saveArticle_DebeLanzarExcepcion_CuandoHayCategoriasDuplicadas() {
        // Arrange
        Category categoria = new Category(1L, "Electrónicos", "Dispositivos");
        Article articuloConCategoriasDuplicadas = new Article(1L, "Laptop", "Descripción válida", 10, 1500.0,
                new Brand(1L, "NombreMarca", "DescripcionMarca"),
                List.of(categoria, categoria)
        );

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articuloConCategoriasDuplicadas));

        assertEquals(ArticleUseCaseConstants.DUPLICATE_CATEGORIES_MESSAGE, excepcion.getMessage());
    }

    @Test
    void saveArticle_DebeLanzarExcepcion_CuandoHayMasDeTresCategorias() {
        // Arrange
        Article articuloConDemasiadasCategorias = new Article(1L, "Laptop", "Descripción válida", 10, 1500.0,
                new Brand(1L, "NombreMarca", "DescripcionMarca"),
                List.of(
                        new Category(1L, "Electrónicos", "Dispositivos"),
                        new Category(2L, "Computadoras", "Laptops"),
                        new Category(3L, "Gadgets", "Varios gadgets"),
                        new Category(4L, "Oficina", "Equipos de oficina")
                )
        );

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articuloConDemasiadasCategorias));

        assertEquals(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE, excepcion.getMessage());
    }

    @Test
    void saveArticle_DebeLanzarExcepcion_CuandoNoHayCategorias() {
        // Arrange
        Article articuloSinCategorias = new Article(1L, "Laptop", "Descripción válida", 10, 1500.0,
                new Brand(1L, "NombreMarca", "DescripcionMarca"),
                List.of()
        );

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class,
                () -> articleUseCase.saveArticle(articuloSinCategorias));

        assertEquals(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE, excepcion.getMessage());
    }
}