package com.emzon.user.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AddUserRequestTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void whenAllFieldsValid_ShouldHaveNoViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "Password1@"
        );

        var violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenNullFields_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        var violations = validator.validate(request);
        assertEquals(7, violations.size());
    }

    @Test
    void whenEmptyFields_ShouldHaveViolations() {
        // Arrange
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        AddUserRequest request = new AddUserRequest(
                "",             // name
                "",             // lastName
                "",             // documentId
                "",             // phone
                null,           // birthDate
                "",             // email
                ""              // password
        );

        // Act
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);

        // Assert
        assertNotNull(violations, "El conjunto de violaciones no debería ser null");
        assertEquals(7, violations.size(), "Deberían haber 7 violaciones");

        Map<String, String> violationMap = violations.stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        assertAll("Verificar todas las violaciones",
                () -> assertTrue(violationMap.containsKey("name"), "Debería contener violación para name"),
                () -> assertTrue(violationMap.containsKey("lastName"), "Debería contener violación para lastName"),
                () -> assertTrue(violationMap.containsKey("documentId"), "Debería contener violación para documentId"),
                () -> assertTrue(violationMap.containsKey("phone"), "Debería contener violación para phone"),
                () -> assertTrue(violationMap.containsKey("birthDate"), "Debería contener violación para birthDate"),
                () -> assertTrue(violationMap.containsKey("email"), "Debería contener violación para email"),
                () -> assertTrue(violationMap.containsKey("password"), "Debería contener violación para password")
        );
    }

    @Test
    void whenInvalidName_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan123",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "Password1@"
        );

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("El nombre solo debe contener letras y espacios")));
    }

    @Test
    void whenInvalidPhone_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "123456",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "Password1@"
        );

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("teléfono")));
    }

    @Test
    void whenInvalidEmail_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "invalid-email",
                "Password1@"
        );

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("correo electrónico")));
    }

    @Test
    void whenInvalidPassword_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "weak"
        );

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("contraseña")));
    }

    @Test
    void whenFutureBirthDate_ShouldHaveViolations() {
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().plusDays(1),
                "juan.perez@example.com",
                "Password1@"
        );

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("La fecha de nacimiento debe ser en el pasado")));
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        AddUserRequest request = new AddUserRequest(
                "Initial",
                "Initial",
                "Initial",
                "Initial",
                LocalDate.now(),
                "Initial",
                "Initial"
        );

        String newName = "Juan";
        String newLastName = "Pérez";
        String newDocumentId = "1234567890";
        String newPhone = "+573005698325";
        LocalDate newBirthDate = LocalDate.now().minusYears(20);
        String newEmail = "juan.perez@example.com";
        String newPassword = "Password1@";

        // Act
        request.setName(newName);
        request.setLastName(newLastName);
        request.setDocumentId(newDocumentId);
        request.setPhone(newPhone);
        request.setBirthDate(newBirthDate);
        request.setEmail(newEmail);
        request.setPassword(newPassword);

        // Assert
        assertEquals(newName, request.getName());
        assertEquals(newLastName, request.getLastName());
        assertEquals(newDocumentId, request.getDocumentId());
        assertEquals(newPhone, request.getPhone());
        assertEquals(newBirthDate, request.getBirthDate());
        assertEquals(newEmail, request.getEmail());
        assertEquals(newPassword, request.getPassword());
    }

    @Test
    void testConstructor() {
        // Arrange
        String name = "Juan";
        String lastName = "Pérez";
        String documentId = "1234567890";
        String phone = "+573005698325";
        LocalDate birthDate = LocalDate.now().minusYears(20);
        String email = "juan.perez@example.com";
        String password = "Password1@";

        // Act
        AddUserRequest request = new AddUserRequest(
                name,
                lastName,
                documentId,
                phone,
                birthDate,
                email,
                password
        );

        // Assert
        assertEquals(name, request.getName());
        assertEquals(lastName, request.getLastName());
        assertEquals(documentId, request.getDocumentId());
        assertEquals(phone, request.getPhone());
        assertEquals(birthDate, request.getBirthDate());
        assertEquals(email, request.getEmail());
        assertEquals(password, request.getPassword());
    }
}