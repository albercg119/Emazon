package com.emzon.user.adapters.jpa.mysql.repository;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IUserRepositoryTest {

    @Mock
    private IUserRepository userRepository;

    @Test
    void existsByEmail_CuandoExisteEmail_DebeRetornarTrue() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean exists = userRepository.existsByEmail(email);

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByEmail_CuandoNoExisteEmail_DebeRetornarFalse() {
        // Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        // Act
        boolean exists = userRepository.existsByEmail("noexiste@example.com");

        // Assert
        assertFalse(exists);
    }

    @Test
    void existsByDocumentId_CuandoExisteDocumento_DebeRetornarTrue() {
        // Arrange
        String documentId = "1234567890";
        when(userRepository.existsByDocumentId(documentId)).thenReturn(true);

        // Act
        boolean exists = userRepository.existsByDocumentId(documentId);

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByDocumentId_CuandoNoExisteDocumento_DebeRetornarFalse() {
        // Arrange
        when(userRepository.existsByDocumentId(anyString())).thenReturn(false);

        // Act
        boolean exists = userRepository.existsByDocumentId("9876543210");

        // Assert
        assertFalse(exists);
    }
}