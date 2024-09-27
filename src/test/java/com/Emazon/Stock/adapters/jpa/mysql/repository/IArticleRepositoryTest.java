package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class IArticleRepositoryTest {

    @Mock
    private IArticleRepository articleRepository;

    private ArticleEntity articleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Simulando un artículo
        articleEntity = new ArticleEntity();
        articleEntity.setId(1L);
        articleEntity.setNombre("Artículo de Prueba");
        articleEntity.setDescripcion("Descripción del artículo");
    }

    @Test
    void testFindByNombre_ReturnsArticle() {
        // Configura el mock para devolver el artículo cuando se llame a findByNombre
        when(articleRepository.findByNombre(anyString())).thenReturn(Optional.of(articleEntity));

        // Ejecuta el método findByNombre
        Optional<ArticleEntity> result = articleRepository.findByNombre("Artículo de Prueba");

        // Verifica que el artículo devuelto sea el correcto
        assertTrue(result.isPresent());
        assertEquals(articleEntity.getNombre(), result.get().getNombre());
        assertEquals(articleEntity.getDescripcion(), result.get().getDescripcion());
    }

    @Test
    void testFindByNombre_ReturnsEmpty() {
        // Configura el mock para devolver un Optional vacío cuando no se encuentra el artículo
        when(articleRepository.findByNombre(anyString())).thenReturn(Optional.empty());

        // Ejecuta el método findByNombre
        Optional<ArticleEntity> result = articleRepository.findByNombre("Nombre no existente");

        // Verifica que el resultado esté vacío
        assertTrue(result.isEmpty());
    }
}