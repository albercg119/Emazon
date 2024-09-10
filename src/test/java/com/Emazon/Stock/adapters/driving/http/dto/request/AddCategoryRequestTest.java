package com.Emazon.Stock.adapters.driving.http.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCategoryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAddCategoryRequest() {
        AddCategoryRequest validRequest = new AddCategoryRequest("Electronics", "Devices and gadgets");

        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(validRequest);

        assertTrue(violations.isEmpty(), "There should be no validation violations");
    }

    @Test
    public void testInvalidAddCategoryRequest() {
        AddCategoryRequest invalidRequest = new AddCategoryRequest("", "");

        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(invalidRequest);

        assertTrue(violations.size() > 0, "There should be validation violations");

        // Check specific violations
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("El nombre de la categoría es obligatorio.")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description") && v.getMessage().equals("La descripción de la categoría es obligatoria.")));
    }
}