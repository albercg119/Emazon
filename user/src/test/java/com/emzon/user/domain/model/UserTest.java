package com.emzon.user.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("Should return true when user is exactly 18 years old")
    void isAdult_ExactlyEighteen_ShouldReturnTrue() {
        // Arrange
        User user = new User(1L, "Test", "User", "123", "123",
                LocalDate.now().minusYears(18), "test@test.com", "pass", Role.AUX_BODEGA);

        // Act & Assert
        assertTrue(user.isAdult());
    }

    @Test
    @DisplayName("Should return true when user is over 18 years old")
    void isAdult_OverEighteen_ShouldReturnTrue() {
        // Arrange
        User user = new User(1L, "Test", "User", "123", "123",
                LocalDate.now().minusYears(19), "test@test.com", "pass", Role.AUX_BODEGA);

        // Act & Assert
        assertTrue(user.isAdult());
    }

    @Test
    @DisplayName("Should return false when user is under 18 years old")
    void isAdult_UnderEighteen_ShouldReturnFalse() {
        // Arrange
        User user = new User(1L, "Test", "User", "123", "123",
                LocalDate.now().minusYears(17), "test@test.com", "pass", Role.AUX_BODEGA);

        // Act & Assert
        assertFalse(user.isAdult());
    }

    @Test
    @DisplayName("Should handle edge case of yesterday's date")
    void isAdult_AlmostEighteen_ShouldReturnFalse() {
        // Arrange
        User user = new User(1L, "Test", "User", "123", "123",
                LocalDate.now().minusYears(18).plusDays(1), "test@test.com", "pass", Role.AUX_BODEGA);

        // Act & Assert
        assertFalse(user.isAdult());
    }

    @Test
    @DisplayName("Should handle null birthDate")
    void isAdult_NullBirthDate_ShouldThrowException() {
        // Arrange
        User user = new User(1L, "Test", "User", "123", "123",
                null, "test@test.com", "pass", Role.AUX_BODEGA);

        // Act & Assert
        assertThrows(NullPointerException.class, user::isAdult);
    }
}