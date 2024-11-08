package com.emzon.user.adapters.jpa.mysql.repository;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IUserRepositoryTest {

    @Mock
    private IUserRepository userRepository;

    private UserEntity testUser;
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_DOCUMENT = "1234567890";

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setName("Test");
        testUser.setLastName("User");
        testUser.setDocumentId(TEST_DOCUMENT);
        testUser.setPhone("+573001234567");
        testUser.setBirthDate(LocalDate.now().minusYears(20));
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword("password123");
        testUser.setRole(Role.CLIENT);
    }

    @Test
    void existsByEmail_WhenEmailExists_ShouldReturnTrue() {
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);
        assertTrue(userRepository.existsByEmail(TEST_EMAIL));
        verify(userRepository).existsByEmail(TEST_EMAIL);
    }

    @Test
    void existsByEmail_WhenEmailDoesNotExist_ShouldReturnFalse() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertFalse(userRepository.existsByEmail("nonexistent@example.com"));
        verify(userRepository).existsByEmail("nonexistent@example.com");
    }

    @Test
    void existsByEmail_WithNullEmail_ShouldReturnFalse() {
        when(userRepository.existsByEmail(null)).thenReturn(false);
        assertFalse(userRepository.existsByEmail(null));
        verify(userRepository).existsByEmail(null);
    }

    @Test
    void existsByDocumentId_WhenDocumentExists_ShouldReturnTrue() {
        when(userRepository.existsByDocumentId(TEST_DOCUMENT)).thenReturn(true);
        assertTrue(userRepository.existsByDocumentId(TEST_DOCUMENT));
        verify(userRepository).existsByDocumentId(TEST_DOCUMENT);
    }

    @Test
    void existsByDocumentId_WhenDocumentDoesNotExist_ShouldReturnFalse() {
        when(userRepository.existsByDocumentId(anyString())).thenReturn(false);
        assertFalse(userRepository.existsByDocumentId("9876543210"));
        verify(userRepository).existsByDocumentId("9876543210");
    }

    @Test
    void existsByDocumentId_WithNullDocument_ShouldReturnFalse() {
        when(userRepository.existsByDocumentId(null)).thenReturn(false);
        assertFalse(userRepository.existsByDocumentId(null));
        verify(userRepository).existsByDocumentId(null);
    }

    @Test
    void findByEmail_WhenUserExists_ShouldReturnUser() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        Optional<UserEntity> foundUser = userRepository.findByEmail(TEST_EMAIL);

        assertTrue(foundUser.isPresent());
        UserEntity user = foundUser.get();
        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_DOCUMENT, user.getDocumentId());
        verify(userRepository).findByEmail(TEST_EMAIL);
    }

    @Test
    void findByEmail_WhenUserDoesNotExist_ShouldReturnEmpty() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<UserEntity> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertTrue(foundUser.isEmpty());
        verify(userRepository).findByEmail("nonexistent@example.com");
    }

    @Test
    void save_ShouldPersistUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUser);

        UserEntity savedUser = userRepository.save(testUser);

        assertNotNull(savedUser);
        assertEquals(testUser.getEmail(), savedUser.getEmail());
        verify(userRepository).save(testUser);
    }
}