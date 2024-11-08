package com.emzon.user.adapters.driving.http.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    private static final Long TEST_ID = 1L;
    private static final String TEST_NAME = "Juan";
    private static final String TEST_LAST_NAME = "Pérez";
    private static final String TEST_EMAIL = "juan.perez@example.com";
    private static final String TEST_ROLE = "AUX_BODEGA";

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userResponse = new UserResponse(
                TEST_ID,
                TEST_NAME,
                TEST_LAST_NAME,
                TEST_EMAIL,
                TEST_ROLE
        );
    }

    @Test
    void constructor_ShouldInitializeAllFields() {
        // Assert
        assertEquals(TEST_ID, userResponse.getId());
        assertEquals(TEST_NAME, userResponse.getName());
        assertEquals(TEST_LAST_NAME, userResponse.getLastName());
        assertEquals(TEST_EMAIL, userResponse.getEmail());
        assertEquals(TEST_ROLE, userResponse.getRole());
    }

    @Test
    void setAndGetId_ShouldWorkCorrectly() {
        // Arrange
        Long newId = 2L;

        // Act
        userResponse.setId(newId);

        // Assert
        assertEquals(newId, userResponse.getId());
    }

    @Test
    void setAndGetName_ShouldWorkCorrectly() {
        // Arrange
        String newName = "Carlos";

        // Act
        userResponse.setName(newName);

        // Assert
        assertEquals(newName, userResponse.getName());
    }

    @Test
    void setAndGetLastName_ShouldWorkCorrectly() {
        // Arrange
        String newLastName = "García";

        // Act
        userResponse.setLastName(newLastName);

        // Assert
        assertEquals(newLastName, userResponse.getLastName());
    }

    @Test
    void setAndGetEmail_ShouldWorkCorrectly() {
        // Arrange
        String newEmail = "carlos.garcia@example.com";

        // Act
        userResponse.setEmail(newEmail);

        // Assert
        assertEquals(newEmail, userResponse.getEmail());
    }

    @Test
    void setAndGetRole_ShouldWorkCorrectly() {
        // Arrange
        String newRole = "ADMIN";

        // Act
        userResponse.setRole(newRole);

        // Assert
        assertEquals(newRole, userResponse.getRole());
    }

    @Test
    void constructor_WithNullValues_ShouldAcceptNullValues() {
        // Act
        UserResponse nullResponse = new UserResponse(null, null, null, null, null);

        // Assert
        assertNull(nullResponse.getId());
        assertNull(nullResponse.getName());
        assertNull(nullResponse.getLastName());
        assertNull(nullResponse.getEmail());
        assertNull(nullResponse.getRole());
    }

    @Test
    void allSetters_WithNullValues_ShouldAcceptNullValues() {
        // Act
        userResponse.setId(null);
        userResponse.setName(null);
        userResponse.setLastName(null);
        userResponse.setEmail(null);
        userResponse.setRole(null);

        // Assert
        assertNull(userResponse.getId());
        assertNull(userResponse.getName());
        assertNull(userResponse.getLastName());
        assertNull(userResponse.getEmail());
        assertNull(userResponse.getRole());
    }
}