package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.emzon.user.adapters.driving.http.dto.response.UserResponse;
import com.emzon.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.emzon.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.emzon.user.adapters.utilities.UserControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private AddUserRequest mockRequest;
    private User mockUser;
    private UserResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockRequest = mock(AddUserRequest.class);
        mockUser = mock(User.class);
        mockResponse = mock(UserResponse.class);
    }

    @Test
    void createAuxBodegaUser_ShouldReturnCreatedStatus() {
        // Arrange
        when(userRequestMapper.toUser(any(AddUserRequest.class))).thenReturn(mockUser);
        when(userResponseMapper.toResponse(any(User.class))).thenReturn(mockResponse);
        doNothing().when(userServicePort).createAuxBodegaUser(any(User.class));

        // Act
        ResponseEntity<UserResponse> response = userRestControllerAdapter.createAuxBodegaUser(mockRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());

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
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRestControllerAdapter.createAuxBodegaUser(mockRequest)
        );
        assertEquals("Invalid request", exception.getMessage());

        // Verify
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
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRestControllerAdapter.createAuxBodegaUser(mockRequest)
        );
        assertEquals("Service error", exception.getMessage());

        // Verify
        verify(userRequestMapper).toUser(mockRequest);
        verify(userResponseMapper, never()).toResponse(any());
    }

    @Test
    void testSwaggerAnnotations() {
        // Test @Operation annotation
        Operation operation = MethodUtils.getMethodsWithAnnotation(UserRestControllerAdapter.class, Operation.class)[0]
                .getAnnotation(Operation.class);
        assertNotNull(operation);
        assertEquals(UserControllerConstants.OPERATION_SUMMARY, operation.summary());
        assertEquals(UserControllerConstants.OPERATION_DESCRIPTION, operation.description());

        // Test @ApiResponses annotation
        ApiResponses responses = MethodUtils.getMethodsWithAnnotation(UserRestControllerAdapter.class, ApiResponses.class)[0]
                .getAnnotation(ApiResponses.class);
        assertNotNull(responses);
        assertTrue(Arrays.stream(responses.value())
                .anyMatch(response -> response.responseCode().equals(UserControllerConstants.RESPONSE_201_CODE)));
    }

    @Test
    void testRequestMappingAnnotation() {
        PostMapping postMapping = MethodUtils.getMethodsWithAnnotation(UserRestControllerAdapter.class, PostMapping.class)[0]
                .getAnnotation(PostMapping.class);
        assertNotNull(postMapping);
        assertEquals(UserControllerConstants.CREATE_PATH, postMapping.value()[0]);
    }
}