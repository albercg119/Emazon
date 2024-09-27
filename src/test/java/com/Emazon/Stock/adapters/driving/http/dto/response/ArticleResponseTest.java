package com.Emazon.Stock.adapters.driving.http.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleResponseTest {

    @InjectMocks
    private ArticleResponse articleResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Creación de datos de prueba
        Long id = 1L;
        String name = "Artículo de prueba";
        String description = "Descripción del artículo de prueba";
        Double price = 99.99;
        String brandName = "Marca de prueba";
        Long categoryId = 1L;
        String categoryName = "Categoría de prueba";
        CategoryResponse categoryResponse = new CategoryResponse(categoryId, categoryName, null);
        List<CategoryResponse> categories = List.of(categoryResponse);



        articleResponse.setId(id);
        articleResponse.setName(name);
        articleResponse.setDescription(description);
        articleResponse.setPrice(price);
        articleResponse.setBrandName(brandName);
        articleResponse.setCategories(categories);


        assertEquals(id, articleResponse.getId());
        assertEquals(name, articleResponse.getName());
        assertEquals(description, articleResponse.getDescription());
        assertEquals(price, articleResponse.getPrice());
        assertEquals(brandName, articleResponse.getBrandName());
        assertEquals(categories, articleResponse.getCategories());
    }

    @Test
    void testNullValues() {

        assertNull(articleResponse.getId());
        assertNull(articleResponse.getName());
        assertNull(articleResponse.getDescription());
        assertNull(articleResponse.getPrice());
        assertNull(articleResponse.getBrandName());
        assertNull(articleResponse.getCategories());
    }
}