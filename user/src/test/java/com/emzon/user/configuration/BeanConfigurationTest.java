package com.emzon.user.configuration;

import com.emzon.user.adapters.jpa.mysql.adapter.UserAdapter;
import com.emzon.user.adapters.jpa.mysql.mapper.IUserEntityMapper;
import com.emzon.user.adapters.jpa.mysql.repository.IUserRepository;
import com.emzon.user.domain.spi.IUserPersistencePort;
import com.emzon.user.domain.usecase.UserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Test
    void userPersistencePort_ShouldReturnUserAdapter() {
        // Arrange
        BeanConfiguration configuration = new BeanConfiguration();

        // Act
        IUserPersistencePort result = configuration.userPersistencePort(
                userRepository,
                userEntityMapper,
                passwordEncoder
        );

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof UserAdapter);
    }

    @Test
    void userServicePort_ShouldReturnUserUseCase() {
        // Arrange
        BeanConfiguration configuration = new BeanConfiguration();

        // Act
        var result = configuration.userServicePort(userPersistencePort);

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof UserUseCase);
    }
}