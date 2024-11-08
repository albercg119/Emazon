package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.ClientControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ClientController clientController;

    private static final String TEST_EMAIL = "client@test.com";

    @BeforeEach
    void setUp() {
        when(authentication.getName()).thenReturn(TEST_EMAIL);
    }

    // Add to Cart Tests
    @Test
    void addToCart_ShouldReturnOk_WhenUserIsClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(true);

        ResponseEntity<?> response = clientController.addToCart(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ClientControllerConstants.PRODUCT_ADDED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void addToCart_ShouldReturnForbidden_WhenUserIsNotClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(false);

        ResponseEntity<?> response = clientController.addToCart(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(ClientControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    // Remove from Cart Tests
    @Test
    void removeFromCart_ShouldReturnOk_WhenUserIsClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(true);

        ResponseEntity<?> response = clientController.removeFromCart(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ClientControllerConstants.PRODUCT_REMOVED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void removeFromCart_ShouldReturnForbidden_WhenUserIsNotClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(false);

        ResponseEntity<?> response = clientController.removeFromCart(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(ClientControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    // Make Purchase Tests
    @Test
    void makePurchase_ShouldReturnOk_WhenUserIsClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(true);

        ResponseEntity<?> response = clientController.makePurchase(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ClientControllerConstants.PURCHASE_COMPLETED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void makePurchase_ShouldReturnForbidden_WhenUserIsNotClient() {
        when(userServicePort.isClient(TEST_EMAIL)).thenReturn(false);

        ResponseEntity<?> response = clientController.makePurchase(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(ClientControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isClient(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void allMethods_ShouldHandleNullAuthenticationName() {
        when(authentication.getName()).thenReturn(null);
        when(userServicePort.isClient(null)).thenReturn(false);

        ResponseEntity<?> addResponse = clientController.addToCart(authentication);
        ResponseEntity<?> removeResponse = clientController.removeFromCart(authentication);
        ResponseEntity<?> purchaseResponse = clientController.makePurchase(authentication);

        assertEquals(HttpStatus.FORBIDDEN, addResponse.getStatusCode());
        assertEquals(HttpStatus.FORBIDDEN, removeResponse.getStatusCode());
        assertEquals(HttpStatus.FORBIDDEN, purchaseResponse.getStatusCode());
        verify(userServicePort, times(3)).isClient(null);
        verify(authentication, times(3)).getName();
    }

    @Test
    void constructorTest() {
        assertDoesNotThrow(() -> {
            ClientController controller = new ClientController(userServicePort);
            assertNotNull(controller);
            assertNotNull(controller.getClass().getDeclaredField("userServicePort"));
        });
    }
}