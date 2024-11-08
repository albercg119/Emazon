package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.AdminControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(authentication.getName()).thenReturn("admin@test.com");
    }

    @Test
    void createCategory_WhenUserIsAdmin_ReturnsOk() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(true);

        ResponseEntity<?> response = adminController.createCategory(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AdminControllerConstants.CATEGORY_CREATED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createCategory_WhenUserIsNotAdmin_ReturnsForbidden() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(false);

        ResponseEntity<?> response = adminController.createCategory(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AdminControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createBrand_WhenUserIsAdmin_ReturnsOk() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(true);

        ResponseEntity<?> response = adminController.createBrand(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AdminControllerConstants.BRAND_CREATED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createBrand_WhenUserIsNotAdmin_ReturnsForbidden() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(false);

        ResponseEntity<?> response = adminController.createBrand(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AdminControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createArticle_WhenUserIsAdmin_ReturnsOk() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(true);

        ResponseEntity<?> response = adminController.createArticle(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AdminControllerConstants.ARTICLE_CREATED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createArticle_WhenUserIsNotAdmin_ReturnsForbidden() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(false);

        ResponseEntity<?> response = adminController.createArticle(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AdminControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createAuxBodega_WhenUserIsAdmin_ReturnsOk() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(true);

        ResponseEntity<?> response = adminController.createAuxBodega(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AdminControllerConstants.AUX_BODEGA_CREATED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }

    @Test
    void createAuxBodega_WhenUserIsNotAdmin_ReturnsForbidden() {
        when(userServicePort.isAdmin("admin@test.com")).thenReturn(false);

        ResponseEntity<?> response = adminController.createAuxBodega(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AdminControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAdmin("admin@test.com");
    }
}