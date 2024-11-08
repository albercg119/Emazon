package com.emzon.user.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigurationTest {

    @Test
    void passwordEncoder_ShouldReturnBCryptPasswordEncoder() {
        // Arrange
        SecurityConfiguration configuration = new SecurityConfiguration();

        // Act
        var result = configuration.passwordEncoder();

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof BCryptPasswordEncoder);
    }

    @Test
    void passwordEncoder_ShouldProduceValidHashes() {
        // Arrange
        SecurityConfiguration configuration = new SecurityConfiguration();
        String rawPassword = "testPassword123";

        // Act
        var encoder = configuration.passwordEncoder();
        String hashedPassword = encoder.encode(rawPassword);

        // Assert
        assertNotNull(hashedPassword);
        assertNotEquals(rawPassword, hashedPassword);
        assertTrue(encoder.matches(rawPassword, hashedPassword));
    }
}