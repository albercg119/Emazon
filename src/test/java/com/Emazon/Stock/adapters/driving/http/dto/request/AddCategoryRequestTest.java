package com.Emazon.Stock.adapters.driving.http.dto.request;

import com.Emazon.Stock.adapters.utilities.AddCategoryRequestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AddCategoryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAddCategoryRequest() {
        // Arrange
        AddCategoryRequest validRequest = new AddCategoryRequest("Electronics", "Devices and gadgets");

        // Act
        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(validRequest);

        // Assert
        assertTrue(violations.isEmpty(), "There should be no validation violations");
    }

    @Test
    public void testInvalidAddCategoryRequest() {
        // Arrange
        AddCategoryRequest invalidRequest = new AddCategoryRequest("", "");

        // Act
        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(invalidRequest);

        // Assert
        assertTrue(violations.size() > 0, "There should be validation violations");

        // Check specific violations
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(AddCategoryRequestConstants.NAME_FIELD) &&
                        v.getMessage().equals(AddCategoryRequestConstants.CATEGORY_NAME_REQUIRED)));

        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(AddCategoryRequestConstants.DESCRIPTION_FIELD) &&
                        v.getMessage().equals(AddCategoryRequestConstants.CATEGORY_DESCRIPTION_REQUIRED)));
    }

    @Test
    public void testCategoryNameMaxLength() {
        // Arrange
        String longName = "a".repeat(AddCategoryRequestConstants.CATEGORY_NAME_MAX_LENGTH + 1);
        AddCategoryRequest invalidRequest = new AddCategoryRequest(longName, "Valid description");

        // Act
        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(invalidRequest);

        // Assert
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(AddCategoryRequestConstants.NAME_FIELD) &&
                        v.getMessage().equals(AddCategoryRequestConstants.CATEGORY_NAME_MAX_SIZE)));
    }

    @Test
    public void testCategoryDescriptionMaxLength() {
        // Arrange
        String longDescription = "a".repeat(AddCategoryRequestConstants.CATEGORY_DESCRIPTION_MAX_LENGTH + 1);
        AddCategoryRequest invalidRequest = new AddCategoryRequest("Valid name", longDescription);

        // Act
        Set<javax.validation.ConstraintViolation<AddCategoryRequest>> violations = validator.validate(invalidRequest);

        // Assert
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(AddCategoryRequestConstants.DESCRIPTION_FIELD) &&
                        v.getMessage().equals(AddCategoryRequestConstants.CATEGORY_DESCRIPTION_MAX_SIZE)));
    }

    @Test
    public void testGetters() {
        // Arrange
        String name = "Electronics";
        String description = "Devices and gadgets";
        AddCategoryRequest request = new AddCategoryRequest(name, description);

        // Act & Assert
        assertEquals(name, request.getName());
        assertEquals(description, request.getDescription());
    }
}