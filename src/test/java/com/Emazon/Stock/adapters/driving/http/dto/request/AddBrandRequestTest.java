package com.Emazon.Stock.adapters.driving.http.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBrandRequestTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAddBrandRequest() {
        // Arrange
        AddBrandRequest validRequest = new AddBrandRequest("Apple", "Technology and devices");

        // Act
        Set<javax.validation.ConstraintViolation<AddBrandRequest>> violations = validator.validate(validRequest);

        // Assert
        assertTrue(violations.isEmpty(), "There should be no validation violations");
    }

    @Test
    public void testInvalidAddBrandRequest() {
        // Arrange
        AddBrandRequest invalidRequest = new AddBrandRequest("", "");

        // Act
        Set<javax.validation.ConstraintViolation<AddBrandRequest>> violations = validator.validate(invalidRequest);

        // Assert
        assertTrue(violations.size() > 0, "There should be validation violations");

        // Check specific violations
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("El nombre de la marca es obligatorio.")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description") && v.getMessage().equals("La descripción de la marca es obligatoria.")));
    }
}