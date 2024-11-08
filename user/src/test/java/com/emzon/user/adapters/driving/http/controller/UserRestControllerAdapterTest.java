package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.driving.http.controller.UserRestControllerAdapter;
import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.emzon.user.adapters.driving.http.dto.response.UserResponse;
import com.emzon.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.emzon.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.emzon.user.adapters.utilities.UserControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRestControllerAdapterTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;

    @Mock
    private AddUserRequest mockRequest;

    @Mock
    private User mockUser;

    @Mock
    private UserResponse mockResponse;

    @BeforeEach
    void setUp() {
        // La configuración básica ya está hecha con @Mock y @InjectMocks
    }

    // Tests para el endpoint createAuxBodegaUser
    @Test
    void createAuxBodegaUser_ShouldReturnCreatedStatus() {
        // Arrange
        when(userRequestMapper.toUser(any(AddUserRequest.class))).thenReturn(mockUser);
        when(userResponseMapper.toResponse(any(User.class))).thenReturn(mockResponse);
        doNothing().when(userServicePort).createAuxBodegaUser(any(User.class));

        // Act
        ResponseEntity<UserResponse> response = userRestControllerAdapter.createAuxBodegaUser(mockRequest);

        // Assert
        assertAll("Verificar creación de usuario auxiliar de bodega",
                () -> assertNotNull(response, "La respuesta no debería ser null"),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                        "El código de estado debería ser CREATED"),
                () -> assertEquals(mockResponse, response.getBody(),
                        "El cuerpo de la respuesta debería coincidir con la respuesta mockada")
        );

        // Verify
        verify(userRequestMapper).toUser(mockRequest);
        verify(userServicePort).createAuxBodegaUser(mockUser);
        verify(userResponseMapper).toResponse(mockUser);
    }

    @Test
    void createAuxBodegaUser_WhenMapperThrowsException_ShouldPropagateException() {
        // Arrange
        when(userRequestMapper.toUser(any(AddUserRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid request"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userRestControllerAdapter.createAuxBodegaUser(mockRequest),
                "Debería lanzar IllegalArgumentException");

        assertEquals("Invalid request", exception.getMessage(),
                "El mensaje de error debería coincidir");

        verify(userRequestMapper).toUser(mockRequest);
        verify(userServicePort, never()).createAuxBodegaUser(any());
        verify(userResponseMapper, never()).toResponse(any());
    }

    @Test
    void createAuxBodegaUser_WhenServiceThrowsException_ShouldPropagateException() {
        // Arrange
        when(userRequestMapper.toUser(any(AddUserRequest.class))).thenReturn(mockUser);
        doThrow(new IllegalArgumentException("Service error"))
                .when(userServicePort).createAuxBodegaUser(any(User.class));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userRestControllerAdapter.createAuxBodegaUser(mockRequest),
                "Debería lanzar IllegalArgumentException");

        assertEquals("Service error", exception.getMessage(),
                "El mensaje de error debería coincidir");

        verify(userRequestMapper).toUser(mockRequest);
        verify(userServicePort).createAuxBodegaUser(mockUser);
        verify(userResponseMapper, never()).toResponse(any());
    }

    // Tests para el endpoint getUserCount
    @Test
    void getUserCount_ShouldReturnOkStatus() {
        // Arrange
        long expectedCount = 10L;
        when(userServicePort.countUsers()).thenReturn(expectedCount);

        // Act
        ResponseEntity<Long> response = userRestControllerAdapter.getUserCount();

        // Assert
        assertAll("Verificar conteo de usuarios",
                () -> assertNotNull(response, "La respuesta no debería ser null"),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode(),
                        "El código de estado debería ser OK"),
                () -> assertEquals(expectedCount, response.getBody(),
                        "El conteo debería coincidir con el valor esperado")
        );

        verify(userServicePort).countUsers();
    }

    // Tests para anotaciones y configuración de clase
    @Test
    void testClassAnnotations() {
        assertAll("Verificar anotaciones de clase",
                () -> assertNotNull(UserRestControllerAdapter.class.getAnnotation(RestController.class),
                        "Debería tener anotación @RestController"),

                () -> {
                    RequestMapping mapping = UserRestControllerAdapter.class.getAnnotation(RequestMapping.class);
                    assertNotNull(mapping, "Debería tener anotación @RequestMapping");
                    assertEquals(UserControllerConstants.BASE_PATH, mapping.value()[0],
                            "El path base debería coincidir con la constante");
                },

                () -> {
                    Tag tag = UserRestControllerAdapter.class.getAnnotation(Tag.class);
                    assertNotNull(tag, "Debería tener anotación @Tag");
                    assertEquals(UserControllerConstants.TAG_NAME, tag.name(),
                            "El nombre del tag debería coincidir");
                    assertEquals(UserControllerConstants.TAG_DESCRIPTION, tag.description(),
                            "La descripción del tag debería coincidir");
                }
        );
    }

    @Test
    void testCreateMethodAnnotations() {
        Method createMethod = Arrays.stream(UserRestControllerAdapter.class.getDeclaredMethods())
                .filter(method -> "createAuxBodegaUser".equals(method.getName()))
                .findFirst()
                .orElseThrow();

        assertAll("Verificar anotaciones del método create",
                () -> {
                    Operation operation = createMethod.getAnnotation(Operation.class);
                    assertNotNull(operation, "Debería tener anotación @Operation");
                    assertEquals(UserControllerConstants.OPERATION_SUMMARY, operation.summary(),
                            "El resumen de la operación debería coincidir");
                    assertEquals(UserControllerConstants.OPERATION_DESCRIPTION, operation.description(),
                            "La descripción de la operación debería coincidir");
                },

                () -> {
                    ApiResponses responses = createMethod.getAnnotation(ApiResponses.class);
                    assertNotNull(responses, "Debería tener anotación @ApiResponses");
                    assertTrue(Arrays.stream(responses.value())
                                    .anyMatch(response -> UserControllerConstants.RESPONSE_201_CODE.equals(response.responseCode())),
                            "Debería incluir respuesta 201");
                },

                () -> {
                    PostMapping mapping = createMethod.getAnnotation(PostMapping.class);
                    assertNotNull(mapping, "Debería tener anotación @PostMapping");
                    assertEquals(UserControllerConstants.CREATE_PATH, mapping.value()[0],
                            "El path de creación debería coincidir");
                }
        );
    }

    @Test
    void testCountMethodAnnotations() {
        Method countMethod = Arrays.stream(UserRestControllerAdapter.class.getDeclaredMethods())
                .filter(method -> "getUserCount".equals(method.getName()))
                .findFirst()
                .orElseThrow();

        assertAll("Verificar anotaciones del método count",
                () -> {
                    Operation operation = countMethod.getAnnotation(Operation.class);
                    assertNotNull(operation, "Debería tener anotación @Operation");
                    assertEquals(UserControllerConstants.COUNT_OPERATION_SUMMARY, operation.summary(),
                            "El resumen de la operación debería coincidir");
                    assertEquals(UserControllerConstants.COUNT_OPERATION_DESCRIPTION, operation.description(),
                            "La descripción de la operación debería coincidir");
                },

                () -> {
                    GetMapping mapping = countMethod.getAnnotation(GetMapping.class);
                    assertNotNull(mapping, "Debería tener anotación @GetMapping");
                    assertEquals(UserControllerConstants.COUNT_PATH, mapping.value()[0],
                            "El path del conteo debería coincidir");
                }
        );
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> {
            UserRestControllerAdapter controller = new UserRestControllerAdapter(
                    userServicePort, userRequestMapper, userResponseMapper);
            assertNotNull(controller, "El controlador no debería ser null");
        });
    }
}
