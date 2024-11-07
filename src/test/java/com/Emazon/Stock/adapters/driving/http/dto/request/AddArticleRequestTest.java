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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    void deberiaSerValidoCuandoTodosLosCamposSonCorrectos() {
        // Arrange
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Smartphone con pantalla 4K");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        addArticleRequest.setCategoryIds(categoryIds);
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertTrue(violaciones.isEmpty(), "El objeto AddArticleRequest debería ser válido");
    }

    @Test
    void deberiaFallarCuandoNombreEsNulo() {
        // Arrange
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");
        assertTrue(violaciones.stream()
                .anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.NAME_NOT_NULL_MESSAGE)));
    }

    @Test
    void deberiaFallarCuandoDescripcionExcedeLongitudMaxima() {
        // Arrange
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("X".repeat(AddArticleRequestConstants.DESCRIPTION_MAX_LENGTH + 1));
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");

        // Imprimir todos los mensajes de violación para depuración
        System.out.println("Mensajes de violación encontrados:");
        violaciones.forEach(v -> System.out.println("- " + v.getMessage()));

        String expectedMessage = String.format(AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE,
                AddArticleRequestConstants.DESCRIPTION_MIN_LENGTH,
                AddArticleRequestConstants.DESCRIPTION_MAX_LENGTH);
        System.out.println("Mensaje esperado: " + expectedMessage);

        Optional<String> actualMessage = violaciones.stream()
                .map(ConstraintViolation::getMessage)
                .filter(expectedMessage::equals)
                .findFirst();

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");
        assertTrue(violaciones.stream()
                        .map(ConstraintViolation::getMessage)
                        .anyMatch(msg -> msg.equals(AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE)),
                "No se encontró el mensaje de validación esperado");
    }


    @Test
    void deberiaFallarCuandoPrecioEsNegativo() {
        // Arrange
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(-10));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");

        // Imprimir todos los mensajes de violación para depuración
        System.out.println("Mensajes de violación encontrados:");
        violaciones.forEach(v -> System.out.println("- " + v.getMessage()));

        String expectedMessage = String.format(AddArticleRequestConstants.PRICE_DECIMAL_MIN_MESSAGE,
                AddArticleRequestConstants.MIN_PRICE);
        System.out.println("Mensaje esperado: " + expectedMessage);

        Optional<String> actualMessage = violaciones.stream()
                .map(ConstraintViolation::getMessage)
                .filter(expectedMessage::equals)
                .findFirst();

        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");
        assertTrue(violaciones.stream()
                        .map(ConstraintViolation::getMessage)
                        .anyMatch(msg -> msg.equals(AddArticleRequestConstants.PRICE_DECIMAL_MIN_MESSAGE)),
                "No se encontró el mensaje de validación esperado");
    }
    

    @Test
    void deberiaFallarCuandoStockEsNulo() {
        // Arrange
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setCategoryIds(List.of(1L));
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");
        assertTrue(violaciones.stream()
                .anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.STOCK_NOT_NULL_MESSAGE)));
    }

    @Test
    void deberiaFallarCuandoCategoriasSonNulas() {
        // Arrange
        addArticleRequest.setName("Smartphone");
        addArticleRequest.setDescription("Descripción válida");
        addArticleRequest.setStockQuantity(10);
        addArticleRequest.setPrice(BigDecimal.valueOf(299.99));
        addArticleRequest.setBrandId(2L);

        // Act
        Set<ConstraintViolation<AddArticleRequest>> violaciones = validator.validate(addArticleRequest);

        // Assert
        assertFalse(violaciones.isEmpty(), "Deberían existir violaciones de validación");
        assertTrue(violaciones.stream()
                .anyMatch(v -> v.getMessage().equals(AddArticleRequestConstants.CATEGORIES_NOT_NULL_MESSAGE)));
    }
}