package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.InitialSetupControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

import static com.emzon.user.domain.model.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitialSetupControllerTest {

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private InitialSetupController initialSetupController;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(
                1L,                     // id
                "admin@test.com",       // email
                "password",             // password
                "John",                 // firstName
                "Doe",                  // lastName
                LocalDate.now(),        // birthDate
                "1234567890",          // phoneNumber
                "address",             // address
                ADMIN                  // role
        );
    }

    // Create Admin Tests
    @Test
    void createAdmin_ShouldReturnOk_WhenEmailNotInUse() {
        when(userServicePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        doNothing().when(userServicePort).createUser(testUser);

        ResponseEntity<?> response = initialSetupController.createAdmin(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(InitialSetupControllerConstants.ADMIN_CREATED_BODY, response.getBody());
        assertEquals(ADMIN, testUser.getRole());
        verify(userServicePort).existsByEmail(testUser.getEmail());
        verify(userServicePort).createUser(testUser);
    }

    @Test
    void createAdmin_ShouldReturnBadRequest_WhenEmailInUse() {
        when(userServicePort.existsByEmail(testUser.getEmail())).thenReturn(true);

        ResponseEntity<?> response = initialSetupController.createAdmin(testUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InitialSetupControllerConstants.EMAIL_IN_USE_BODY, response.getBody());
        verify(userServicePort).existsByEmail(testUser.getEmail());
        verify(userServicePort, never()).createUser(any());
    }

    @Test
    void createFirstAdmin_ShouldReturnOk_WhenNoUsersExist() {
        when(userServicePort.existsAnyUser()).thenReturn(false);
        doNothing().when(userServicePort).createUser(testUser);

        ResponseEntity<?> response = initialSetupController.createFirstAdmin(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(InitialSetupControllerConstants.FIRST_ADMIN_CREATED_BODY, response.getBody());
        assertEquals(ADMIN, testUser.getRole());
        verify(userServicePort).existsAnyUser();
        verify(userServicePort).createUser(testUser);
    }

    @Test
    void createFirstAdmin_ShouldReturnBadRequest_WhenUsersExist() {
        when(userServicePort.existsAnyUser()).thenReturn(true);

        ResponseEntity<?> response = initialSetupController.createFirstAdmin(testUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InitialSetupControllerConstants.SETUP_COMPLETED_BODY, response.getBody());
        verify(userServicePort).existsAnyUser();
        verify(userServicePort, never()).createUser(any());
    }

    @Test
    void testNullUserEmail() {
        User userWithNullEmail = new User(
                1L,                    // id
                "John",                // name
                "Doe",                 // lastName
                "1234567890",         // documentId
                "+573001234567",      // phone
                LocalDate.now(),       // birthDate
                null,                 // email
                "password",           // password
                ADMIN                 // role
        );

        when(userServicePort.existsByEmail(null)).thenReturn(false);
        doNothing().when(userServicePort).createUser(any(User.class));

        ResponseEntity<?> response = initialSetupController.createAdmin(userWithNullEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin user created successfully", response.getBody());
        verify(userServicePort).existsByEmail(null);
        verify(userServicePort).createUser(userWithNullEmail);
    }

    @Test
    void constructorTest() {
        InitialSetupController controller = new InitialSetupController(userServicePort);
        assertNotNull(controller);
    }
}