package com.emzon.user.domain.usecase;

import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.spi.IUserPersistencePort;
import com.emzon.user.domain.utilities.constants.UserUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User validUser;
    private User nullUser;

    @BeforeEach
    void setUp() {
        // Usuario válido para casos positivos
        validUser = new User(
                1L,
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "password123",
                null
        );

        // Usuario nulo para probar casos extremos
        nullUser = null;
    }

    @Test
    void createAuxBodegaUser_WithValidUser_ShouldSetRoleAndSave() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(anyString())).thenReturn(false);
        doNothing().when(userPersistencePort).saveUser(any(User.class));

        // Act
        userUseCase.createAuxBodegaUser(validUser);

        // Assert
        assertEquals(Role.AUX_BODEGA, validUser.getRole());
        verify(userPersistencePort, times(1)).saveUser(validUser);
        verify(userPersistencePort, times(1)).existsByEmail(validUser.getEmail());
        verify(userPersistencePort, times(1)).existsByDocumentId(validUser.getDocumentId());
    }

    @Test
    void createAuxBodegaUser_WithUnderageUser_ShouldThrowException() {
        // Arrange
        User underageUser = new User(
                1L,
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(17),
                "juan.perez@example.com",
                "password123",
                null
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(underageUser));

        assertEquals(UserUseCaseConstants.ADULT_AGE_ERROR, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
        verify(userPersistencePort, never()).existsByEmail(anyString());
        verify(userPersistencePort, never()).existsByDocumentId(anyString());
    }

    @Test
    void createAuxBodegaUser_WithExistingEmail_ShouldThrowException() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(validUser));

        assertEquals(UserUseCaseConstants.EMAIL_EXISTS_ERROR, exception.getMessage());
        verify(userPersistencePort, times(1)).existsByEmail(validUser.getEmail());
        verify(userPersistencePort, never()).existsByDocumentId(anyString());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_WithExistingDocument_ShouldThrowException() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(anyString())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(validUser));

        assertEquals(UserUseCaseConstants.DOCUMENT_EXISTS_ERROR, exception.getMessage());
        verify(userPersistencePort, times(1)).existsByEmail(validUser.getEmail());
        verify(userPersistencePort, times(1)).existsByDocumentId(validUser.getDocumentId());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_WithNullUser_ShouldThrowException() {
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.createAuxBodegaUser(null));

        verify(userPersistencePort, never()).existsByEmail(anyString());
        verify(userPersistencePort, never()).existsByDocumentId(anyString());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_WithNullEmail_ShouldThrowException() {
        // Arrange
        validUser.setEmail(null);
        doThrow(NullPointerException.class)
                .when(userPersistencePort)
                .existsByEmail(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            userUseCase.createAuxBodegaUser(validUser);
        });

        // Verify
        verify(userPersistencePort).existsByEmail(null);
        verify(userPersistencePort, never()).existsByDocumentId(any());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_WithNullDocumentId_ShouldThrowException() {
        // Arrange
        validUser.setDocumentId(null);
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        doThrow(NullPointerException.class)
                .when(userPersistencePort)
                .existsByDocumentId(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            userUseCase.createAuxBodegaUser(validUser);
        });

        // Verify
        verify(userPersistencePort).existsByEmail(validUser.getEmail());
        verify(userPersistencePort).existsByDocumentId(null);
        verify(userPersistencePort, never()).saveUser(any());
    }
}