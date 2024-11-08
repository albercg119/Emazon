package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.AuxBodegaControllerConstants;
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

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuxBodegaControllerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuxBodegaController auxBodegaController;

    private static final String TEST_EMAIL = "auxbodega@test.com";

    @BeforeEach
    void setUp() {
        when(authentication.getName()).thenReturn(TEST_EMAIL);
    }

    @Test
    void addSupply_ShouldReturnOk_WhenUserIsAuxBodega() {
        when(userServicePort.isAuxBodega(TEST_EMAIL)).thenReturn(true);

        ResponseEntity<?> response = auxBodegaController.addSupply(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AuxBodegaControllerConstants.SUPPLY_ADDED_BODY, response.getBody());
        verify(userServicePort).isAuxBodega(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void addSupply_ShouldReturnForbidden_WhenUserIsNotAuxBodega() {
        when(userServicePort.isAuxBodega(TEST_EMAIL)).thenReturn(false);

        ResponseEntity<?> response = auxBodegaController.addSupply(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AuxBodegaControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAuxBodega(TEST_EMAIL);
        verify(authentication).getName();
    }

    @Test
    void addSupply_ShouldHandleNullAuthentication_WhenAuthenticationNameIsNull() {
        when(authentication.getName()).thenReturn(null);
        when(userServicePort.isAuxBodega(null)).thenReturn(false);

        ResponseEntity<?> response = auxBodegaController.addSupply(authentication);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(AuxBodegaControllerConstants.ACCESS_DENIED_BODY, response.getBody());
        verify(userServicePort).isAuxBodega(null);
        verify(authentication).getName();
    }

    @Test
    void constructorTest() {
        // Arrange
        IUserServicePort mockUserServicePort = mock(IUserServicePort.class);

        // Act & Assert
        assertAll("Verificar construcción del controlador",
                () -> {
                    AuxBodegaController controller = assertDoesNotThrow(
                            () -> new AuxBodegaController(mockUserServicePort),
                            "La construcción del controlador no debería lanzar excepciones"
                    );

                    assertNotNull(controller, "El controlador no debería ser null");

                    Field userServicePortField = AuxBodegaController.class
                            .getDeclaredField("userServicePort");
                    userServicePortField.setAccessible(true);

                    Object fieldValue = userServicePortField.get(controller);
                    assertNotNull(fieldValue, "El campo userServicePort no debería ser null");
                    assertEquals(mockUserServicePort, fieldValue,
                            "El userServicePort inyectado debería ser el mismo que el mock");
                }
        );
    }
}