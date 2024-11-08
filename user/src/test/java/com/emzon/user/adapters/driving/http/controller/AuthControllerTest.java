package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.driving.http.dto.request.LoginRequest;
import com.emzon.user.adapters.driving.http.dto.response.JwtResponse;
import com.emzon.user.adapters.utilities.AuthControllerConstants;
import com.emzon.user.configuration.security.JwtTokenUtil;
import com.emzon.user.domain.api.IUserServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");
        userDetails = new User("test@example.com", "password", new ArrayList<>());
    }

    @Test
    void createAuthenticationToken_WhenValidCredentials_ReturnsToken() throws Exception {
        // Arrange
        String testEmail = "test@example.com";
        String testPassword = "password123";
        String expectedToken = "valid.jwt.token";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(testEmail);
        loginRequest.setPassword(testPassword);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                testEmail, testPassword, authorities);

        UsernamePasswordAuthenticationToken expectedAuthToken =
                new UsernamePasswordAuthenticationToken(testEmail, testPassword);

        doNothing().when(authenticationManager).authenticate(eq(expectedAuthToken));
        when(userServicePort.loadUserByUsername(testEmail)).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(expectedToken);

        // Act
        ResponseEntity<?> response = authController.createAuthenticationToken(loginRequest);

        // Assert
        assertAll("Verificar respuesta de autenticación",
                () -> assertNotNull(response, "La respuesta no debería ser null"),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "El código de estado debería ser OK"),
                () -> assertNotNull(response.getBody(), "El cuerpo de la respuesta no debería ser null"),
                () -> assertTrue(response.getBody() instanceof JwtResponse,
                        "El cuerpo debería ser una instancia de JwtResponse"),
                () -> assertEquals(expectedToken, ((JwtResponse) response.getBody()).getToken(),
                        "El token devuelto debería coincidir con el esperado")
        );

        // Verify
        verify(authenticationManager).authenticate(eq(expectedAuthToken));
        verify(userServicePort).loadUserByUsername(testEmail);
        verify(jwtTokenUtil).generateToken(userDetails);
        verifyNoMoreInteractions(authenticationManager, jwtTokenUtil);
    }

    @Test
    void createAuthenticationToken_WhenUserDisabled_ThrowsException() {
        doThrow(new DisabledException("USER_DISABLED"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        Exception exception = assertThrows(Exception.class, () -> {
            authController.createAuthenticationToken(loginRequest);
        });

        assertEquals(AuthControllerConstants.USER_DISABLED_MESSAGE, exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userServicePort, never()).loadUserByUsername(any());
        verify(jwtTokenUtil, never()).generateToken(any());
    }

    @Test
    void createAuthenticationToken_WhenInvalidCredentials_ThrowsException() {
        doThrow(new BadCredentialsException("Invalid Credentials"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        Exception exception = assertThrows(Exception.class, () -> {
            authController.createAuthenticationToken(loginRequest);
        });

        assertEquals(AuthControllerConstants.INVALID_CREDENTIALS_MESSAGE, exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userServicePort, never()).loadUserByUsername(any());
        verify(jwtTokenUtil, never()).generateToken(any());
    }
}