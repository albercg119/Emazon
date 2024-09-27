package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IArticleEntityMapperTest {

    private IArticleEntityMapper articleEntityMapper;

    @BeforeEach
    void setUp() {
        // Inicializamos el mapper utilizando MapStruct
        articleEntityMapper = Mappers.getMapper(IArticleEntityMapper.class);
    }

    @Test
    void testToArticleEntity() {
        // Creamos un modelo Brand y Category de prueba utilizando sus constructores
        Brand brand = new Brand(1L, "Marca de Prueba", "Descripción de Marca");
        Category category = new Category(1L, "Categoría de Prueba", "Descripción de Categoría");

        // Creamos un modelo Article de prueba utilizando su constructor
        Article article = new Article(
                1L,
                "Articulo de Prueba",
                "Descripción de prueba",
                100,
                50.0,
                brand,
                Arrays.asList(category)
        );

        // Mapeamos el modelo Article a ArticleEntity
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);

        // Verificamos que los valores fueron mapeados correctamente
        assertNotNull(articleEntity);
        assertEquals("Articulo de Prueba", articleEntity.getNombre());
        assertEquals("Descripción de prueba", articleEntity.getDescripcion());
        assertEquals(100, articleEntity.getStockQuantity());
        assertEquals(50.0, articleEntity.getPrice());
        assertNotNull(articleEntity.getBrand());
        assertEquals("Marca de Prueba", articleEntity.getBrand().getNombre());
        assertNotNull(articleEntity.getCategories());
        assertEquals(1, articleEntity.getCategories().size());
        assertEquals("Categoría de Prueba", articleEntity.getCategories().get(0).getNombre());
    }

    @Test
    void testToArticle() {
        // Creamos una entidad ArticleEntity de prueba
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setNombre("Articulo de Prueba");
        articleEntity.setDescripcion("Descripción de prueba");
        articleEntity.setStockQuantity(100);
        articleEntity.setPrice(50.0);

        com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity brandEntity = new com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity();
        brandEntity.setNombre("Marca de Prueba");
        articleEntity.setBrand(brandEntity);

        com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity categoryEntity = new com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity();
        categoryEntity.setNombre("Categoría de Prueba");
        articleEntity.setCategories(Arrays.asList(categoryEntity));

        // Mapeamos la entidad ArticleEntity a un modelo Article
        Article article = articleEntityMapper.toArticle(articleEntity);

        // Verificamos que los valores fueron mapeados correctamente
        assertNotNull(article);
        assertEquals("Articulo de Prueba", article.getName());
        assertEquals("Descripción de prueba", article.getDescription());
        assertEquals(100, article.getStockQuantity());
        assertEquals(50.0, article.getPrice());
        assertNotNull(article.getBrand());
        assertEquals("Marca de Prueba", article.getBrand().getNombre());
        assertNotNull(article.getCategories());
        assertEquals(1, article.getCategories().size());
        assertEquals("Categoría de Prueba", article.getCategories().get(0).getNombre());
    }

    @Test
    void testToModelList() {
        // Creamos una lista de ArticleEntity de prueba
        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setNombre("Articulo 1");
        articleEntity1.setDescripcion("Descripción 1");
        articleEntity1.setStockQuantity(50);
        articleEntity1.setPrice(20.0);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setNombre("Articulo 2");
        articleEntity2.setDescripcion("Descripción 2");
        articleEntity2.setStockQuantity(100);
        articleEntity2.setPrice(40.0);

        List<ArticleEntity> articleEntities = Arrays.asList(articleEntity1, articleEntity2);

        // Mapeamos la lista de ArticleEntity a una lista de Article
        List<Article> articles = articleEntityMapper.toModelList(articleEntities);

        // Verificamos que los valores fueron mapeados correctamente
        assertNotNull(articles);
        assertEquals(2, articles.size());

        assertEquals("Articulo 1", articles.get(0).getName());
        assertEquals("Descripción 1", articles.get(0).getDescription());
        assertEquals(50, articles.get(0).getStockQuantity());
        assertEquals(20.0, articles.get(0).getPrice());

        assertEquals("Articulo 2", articles.get(1).getName());
        assertEquals("Descripción 2", articles.get(1).getDescription());
        assertEquals(100, articles.get(1).getStockQuantity());
        assertEquals(40.0, articles.get(1).getPrice());
    }
}