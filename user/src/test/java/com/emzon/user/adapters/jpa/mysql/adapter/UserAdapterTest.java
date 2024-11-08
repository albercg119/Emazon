package com.emzon.user.adapters.jpa.mysql.adapter;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.adapters.jpa.mysql.mapper.IUserEntityMapper;
import com.emzon.user.adapters.jpa.mysql.repository.IUserRepository;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAdapter userAdapter;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    private User user;
    private UserEntity userEntity;
    private static final LocalDate TEST_DATE = LocalDate.now();

    @BeforeEach
    void setUp() {
        // Inicializar User
        user = new User(
                1L,
                "Test",
                "User",
                "123",
                "123456789",
                TEST_DATE,
                "test@test.com",
                "password",
                Role.AUX_BODEGA
        );

        // Inicializar UserEntity con todos los campos requeridos
        userEntity = new UserEntity(
                1L,
                "Test",
                "User",
                "123",
                "123456789",
                TEST_DATE,
                "test@test.com",
                "password",
                Role.AUX_BODEGA
        );
    }

    @Test
    void saveUser_ShouldSaveUserWithEncodedPassword() {
        // Arrange
        String encodedPassword = "encodedPassword";
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        userAdapter.saveUser(user);

        // Assert
        verify(userEntityMapper).toEntity(user);
        verify(passwordEncoder).encode(user.getPassword());
        verify(userRepository).save(userEntityCaptor.capture());

        UserEntity savedEntity = userEntityCaptor.getValue();
        assertEquals(encodedPassword, savedEntity.getPassword());
    }

    @Test
    void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        // Arrange
        String email = "test@test.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean result = userAdapter.existsByEmail(email);

        // Assert
        assertTrue(result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByEmail_ShouldReturnFalse_WhenEmailDoesNotExist() {
        // Arrange
        String email = "test@test.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
        boolean result = userAdapter.existsByEmail(email);

        // Assert
        assertFalse(result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByDocumentId_ShouldReturnTrue_WhenDocumentExists() {
        // Arrange
        String documentId = "123";
        when(userRepository.existsByDocumentId(documentId)).thenReturn(true);

        // Act
        boolean result = userAdapter.existsByDocumentId(documentId);

        // Assert
        assertTrue(result);
        verify(userRepository).existsByDocumentId(documentId);
    }

    @Test
    void existsByDocumentId_ShouldReturnFalse_WhenDocumentDoesNotExist() {
        // Arrange
        String documentId = "123";
        when(userRepository.existsByDocumentId(documentId)).thenReturn(false);

        // Act
        boolean result = userAdapter.existsByDocumentId(documentId);

        // Assert
        assertFalse(result);
        verify(userRepository).existsByDocumentId(documentId);
    }

    @Test
    void saveUser_ShouldHandleNullPassword() {
        // Arrange
        user.setPassword(null);
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(null)).thenThrow(new IllegalArgumentException("Password cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userAdapter.saveUser(user));
        verify(userEntityMapper).toEntity(user);
        verify(passwordEncoder).encode(null);
        verify(userRepository, never()).save(any());
    }

    @Test
    void saveUser_ShouldHandleMapperException() {
        // Arrange
        when(userEntityMapper.toEntity(any(User.class)))
                .thenThrow(new IllegalArgumentException("Mapping error"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userAdapter.saveUser(user));
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void saveUser_ShouldHandleRepositoryException() {
        // Arrange
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");
        when(userRepository.save(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userAdapter.saveUser(user));
        verify(userEntityMapper).toEntity(user);
        verify(passwordEncoder).encode(anyString());
    }

    @Test
    void constructor_ShouldInitializeAllDependencies() {
        // Arrange
        UserAdapter newAdapter = new UserAdapter(userRepository, userEntityMapper, passwordEncoder);

        // Assert
        assertNotNull(newAdapter);
        assertNotNull(ReflectionTestUtils.getField(newAdapter, "userRepository"));
        assertNotNull(ReflectionTestUtils.getField(newAdapter, "userEntityMapper"));
        assertNotNull(ReflectionTestUtils.getField(newAdapter, "passwordEncoder"));
    }
}