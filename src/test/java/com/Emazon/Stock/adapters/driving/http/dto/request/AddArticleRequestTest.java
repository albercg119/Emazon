package com.Emazon.Stock.adapters.driving.http.dto.request;


import com.Emazon.Stock.adapters.utilities.AddArticleRequestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddArticleRequestTest {

    private Validator validator;
    private AddArticleRequest addArticleRequest;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        addArticleRequest = new AddArticleRequest();
    }

    @Test
    void testValidAddArticleRequest() {
        // Configuramos una instancia válida de AddArticleRequest
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Smartphone con pantalla 4K");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        addArticleRequest.setCategoryIds(categoryIds);
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y comprobamos que no haya violaciones
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertTrue(violations.isEmpty(), "El objeto AddArticleRequest debería ser válido");
    }

    @Test
    void testNameNotNull() {
        // Dejamos el nombre como nulo
        addArticleRequest.setName(null);
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y verificamos la violación de la validación
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertFalse(violations.isEmpty(), "El nombre no debería ser nulo");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.NAME_NOT_NULL_MESSAGE)));
    }

    @Test
    void testDescriptionSizeInvalid() {
        // Configuramos una descripción con más de 90 caracteres
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Esta es una descripción muy larga que supera los 90 caracteres y por lo tanto debería invalidar la solicitud");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y comprobamos que haya una violación por longitud
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertFalse(violations.isEmpty(), "La descripción debería ser demasiado larga");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE)));
    }

    @Test
    void testPriceInvalid() {
        // Configuramos un precio inválido
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(-10)); // Precio negativo
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y comprobamos que el precio sea inválido
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertFalse(violations.isEmpty(), "El precio no debería ser válido");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.PRICE_DECIMAL_MIN_MESSAGE)));
    }

    @Test
    void testStockQuantityNull() {
        // Configuramos stockQuantity como nulo
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(null); // Stock nulo
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y comprobamos que haya una violación
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertFalse(violations.isEmpty(), "El stock no debería ser nulo");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.STOCK_NOT_NULL_MESSAGE)));
    }

    @Test
    void testCategoryIdsNotNull() {
        // Configuramos categoryIds como nulo
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(null); // Categorías nulas
        addArticleRequest.setBrandId(2L);

        // Validamos el objeto y comprobamos que haya una violación
        Set<ConstraintViolation<AddArticleRequest>> violations = validator.validate(addArticleRequest);
        assertFalse(violations.isEmpty(), "Las categorías no deberían ser nulas");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.CATEGORIES_NOT_NULL_MESSAGE)));
    }
}