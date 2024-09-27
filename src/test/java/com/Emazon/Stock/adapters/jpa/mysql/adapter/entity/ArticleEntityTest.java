package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ArticleEntityTest {

    @InjectMocks
    private ArticleEntity articleEntity;

    @Mock
    private BrandEntity brandEntity;

    @Mock
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSettersAndGetters() {

        articleEntity.setId(1L);
        articleEntity.setNombre("Articulo de Prueba");
        articleEntity.setDescripcion("Descripción de prueba");
        articleEntity.setStockQuantity(50);
        articleEntity.setPrice(25.0);

        when(brandEntity.getNombre()).thenReturn("Marca de Prueba");
        articleEntity.setBrand(brandEntity);

        List<CategoryEntity> categories = Arrays.asList(categoryEntity);
        articleEntity.setCategories(categories);


        assertEquals(1L, articleEntity.getId());
        assertEquals("Articulo de Prueba", articleEntity.getNombre());
        assertEquals("Descripción de prueba", articleEntity.getDescripcion());
        assertEquals(50, articleEntity.getStockQuantity());
        assertEquals(25.0, articleEntity.getPrice());


        assertNotNull(articleEntity.getBrand());
        assertEquals("Marca de Prueba", articleEntity.getBrand().getNombre());


        assertNotNull(articleEntity.getCategories());
        assertEquals(1, articleEntity.getCategories().size());
    }

    @Test
    void testSetBrand() {

        BrandEntity mockBrand = mock(BrandEntity.class);
        articleEntity.setBrand(mockBrand);


        assertNotNull(articleEntity.getBrand());
        verify(mockBrand, never()).setNombre(anyString());
    }

    @Test
    void testSetCategories() {

        CategoryEntity mockCategory = mock(CategoryEntity.class);
        List<CategoryEntity> categories = Arrays.asList(mockCategory);

        articleEntity.setCategories(categories);


        assertNotNull(articleEntity.getCategories());
        assertEquals(1, articleEntity.getCategories().size());
        verify(mockCategory, never()).setNombre(anyString());
    }
}