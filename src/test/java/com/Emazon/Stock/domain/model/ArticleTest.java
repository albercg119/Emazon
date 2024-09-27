package com.Emazon.Stock.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private Article article;
    private Brand mockBrand;
    private Category category1;
    private Category category2;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        // Arrange
        mockBrand = new Brand(1L, "MockBrand", "BrandDescription");
        category1 = new Category(1L, "Category1", "CategoryDescription1"); // Agrega el tercer argumento
        category2 = new Category(2L, "Category2", "CategoryDescription2"); // Agrega el tercer argumento
        categories = Arrays.asList(category1, category2);

        // Act
        article = new Article(1L, "Laptop", "High-end gaming laptop", 100, 2000.0, mockBrand, categories);
    }

    @Test
    void testGetters() {
        // Assert
        assertEquals(1L, article.getId());
        assertEquals("Laptop", article.getName());
        assertEquals("High-end gaming laptop", article.getDescription());
        assertEquals(100, article.getStockQuantity());
        assertEquals(2000.0, article.getPrice());
        assertEquals(mockBrand, article.getBrand());
        assertEquals(categories, article.getCategories());
    }

    @Test
    void testSetters() {
        // Arrange
        Brand newBrand = new Brand(2L, "NewBrand", "NewBrandDescription");
        Category newCategory = new Category(3L, "NewCategory", "NewCategoryDescription"); // Tercer parámetro añadido
        List<Category> newCategories = Arrays.asList(newCategory);

        // Act
        article.setId(2L);
        article.setName("Smartphone");
        article.setDescription("Latest model smartphone");
        article.setStockQuantity(50);
        article.setPrice(800.0);
        article.setBrand(newBrand);
        article.setCategories(newCategories);

        // Assert
        assertEquals(2L, article.getId());
        assertEquals("Smartphone", article.getName());
        assertEquals("Latest model smartphone", article.getDescription());
        assertEquals(50, article.getStockQuantity());
        assertEquals(800.0, article.getPrice());
        assertEquals(newBrand, article.getBrand());
        assertEquals(newCategories, article.getCategories());
    }

    @Test
    void testNullValuesInSetters() {
        // Act
        article.setName(null);
        article.setDescription(null);
        article.setBrand(null);
        article.setCategories(null);

        // Assert
        assertNull(article.getName());
        assertNull(article.getDescription());
        assertNull(article.getBrand());
        assertNull(article.getCategories());
    }

    @Test
    void testEmptyCategoryList() {
        // Act
        article.setCategories(Arrays.asList());

        // Assert
        assertTrue(article.getCategories().isEmpty());
    }

    @Test
    void testNegativeStockQuantityAndPrice() {
        // Act
        article.setStockQuantity(-10);
        article.setPrice(-500.0);

        // Assert
        assertEquals(-10, article.getStockQuantity());
        assertEquals(-500.0, article.getPrice());
    }
}