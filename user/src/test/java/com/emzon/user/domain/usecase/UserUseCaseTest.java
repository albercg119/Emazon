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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    private User testUser;
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_DOCUMENT = "1234567890";

    @BeforeEach
    void setUp() {
        testUser = new User(
                1L,                     // id
                "John",                 // nombre
                "Doe",                  // apellido
                TEST_DOCUMENT,          // documentId
                "+573001234567",       // teléfono
                LocalDate.now().minusYears(20), // fecha nacimiento
                TEST_EMAIL,            // email
                "password123",         // contraseña
                Role.CLIENT            // rol
        );
    }

    @Test
    void createAuxBodegaUser_Success() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(TEST_DOCUMENT)).thenReturn(false);

        userUseCase.createAuxBodegaUser(testUser);

        assertEquals(Role.AUX_BODEGA, testUser.getRole());
        verify(userPersistencePort).saveUser(testUser);
    }

    @Test
    void createAuxBodegaUser_ThrowsException_WhenUnderAge() {
        testUser = new User(
                1L, "John", "Doe", TEST_DOCUMENT, "+573001234567",
                LocalDate.now().minusYears(17), TEST_EMAIL, "password123", Role.CLIENT
        );

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(testUser));
        assertEquals(UserUseCaseConstants.ADULT_AGE_ERROR, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_ThrowsException_WhenEmailExists() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(testUser));
        assertEquals(UserUseCaseConstants.EMAIL_EXISTS_ERROR, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createAuxBodegaUser_ThrowsException_WhenDocumentExists() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(TEST_DOCUMENT)).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createAuxBodegaUser(testUser));
        assertEquals(UserUseCaseConstants.DOCUMENT_EXISTS_ERROR, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void loadUserByUsername_Success() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        UserDetails result = userUseCase.loadUserByUsername(TEST_EMAIL);

        assertNotNull(result);
        assertEquals(TEST_EMAIL, result.getUsername());
        assertEquals(testUser.getPassword(), result.getPassword());
        assertTrue(result.getAuthorities().isEmpty());
    }

    @Test
    void loadUserByUsername_ThrowsException_WhenUserNotFound() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(null);

        Exception exception = assertThrows(UsernameNotFoundException.class,
                () -> userUseCase.loadUserByUsername(TEST_EMAIL));
        assertEquals(UserUseCaseConstants.USER_NOT_FOUND + TEST_EMAIL, exception.getMessage());
    }

    @Test
    void isAdmin_ReturnsTrue_WhenUserIsAdmin() {
        testUser.setRole(Role.ADMIN);
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertTrue(userUseCase.isAdmin(TEST_EMAIL));
    }

    @Test
    void isAdmin_ReturnsFalse_WhenUserIsNotAdmin() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertFalse(userUseCase.isAdmin(TEST_EMAIL));
    }

    @Test
    void isAdmin_ReturnsFalse_WhenUserNotFound() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(null);

        assertFalse(userUseCase.isAdmin(TEST_EMAIL));
    }

    @Test
    void isAuxBodega_ReturnsTrue_WhenUserIsAuxBodega() {
        testUser.setRole(Role.AUX_BODEGA);
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertTrue(userUseCase.isAuxBodega(TEST_EMAIL));
    }

    @Test
    void isAuxBodega_ReturnsFalse_WhenUserIsNotAuxBodega() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertFalse(userUseCase.isAuxBodega(TEST_EMAIL));
    }

    @Test
    void isAuxBodega_ReturnsFalse_WhenUserNotFound() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(null);

        assertFalse(userUseCase.isAuxBodega(TEST_EMAIL));
    }

    @Test
    void isClient_ReturnsTrue_WhenUserIsClient() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertTrue(userUseCase.isClient(TEST_EMAIL));
    }

    @Test
    void isClient_ReturnsFalse_WhenUserIsNotClient() {
        testUser.setRole(Role.ADMIN);
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(testUser);

        assertFalse(userUseCase.isClient(TEST_EMAIL));
    }

    @Test
    void isClient_ReturnsFalse_WhenUserNotFound() {
        when(userPersistencePort.findByEmail(TEST_EMAIL)).thenReturn(null);

        assertFalse(userUseCase.isClient(TEST_EMAIL));
    }

    @Test
    void existsAnyUser_ReturnsTrue_WhenUsersExist() {
        when(userPersistencePort.countUsers()).thenReturn(1L);

        assertTrue(userUseCase.existsAnyUser());
    }

    @Test
    void existsAnyUser_ReturnsFalse_WhenNoUsers() {
        when(userPersistencePort.countUsers()).thenReturn(0L);

        assertFalse(userUseCase.existsAnyUser());
    }

    @Test
    void createUser_Success() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(TEST_DOCUMENT)).thenReturn(false);

        userUseCase.createUser(testUser);

        verify(userPersistencePort).saveUser(testUser);
    }

    @Test
    void createUser_ThrowsException_WhenEmailExists() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createUser(testUser));
        assertEquals(UserUseCaseConstants.EMAIL_ALREADY_EXISTS, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createUser_ThrowsException_WhenDocumentExists() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(false);
        when(userPersistencePort.existsByDocumentId(TEST_DOCUMENT)).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> userUseCase.createUser(testUser));
        assertEquals(UserUseCaseConstants.DOCUMENT_ID_EXISTS, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void countUsers_ReturnsCorrectCount() {
        long expectedCount = 5L;
        when(userPersistencePort.countUsers()).thenReturn(expectedCount);

        assertEquals(expectedCount, userUseCase.countUsers());
    }

    @Test
    void existsByEmail_ReturnsTrue_WhenEmailExists() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(true);

        assertTrue(userUseCase.existsByEmail(TEST_EMAIL));
    }

    @Test
    void existsByEmail_ReturnsFalse_WhenEmailDoesNotExist() {
        when(userPersistencePort.existsByEmail(TEST_EMAIL)).thenReturn(false);

        assertFalse(userUseCase.existsByEmail(TEST_EMAIL));
    }

    @Test
    void constructorTest() {
        assertNotNull(userUseCase);
        assertNotNull(userPersistencePort);
    }
}