package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.emzon.user.adapters.driving.http.dto.response.UserResponse;
import com.emzon.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.emzon.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.emzon.user.adapters.utilities.UserControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserControllerConstants.BASE_PATH)
@Tag(name = UserControllerConstants.TAG_NAME,
        description = UserControllerConstants.TAG_DESCRIPTION)
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public UserRestControllerAdapter(IUserServicePort userServicePort,
                                     IUserRequestMapper userRequestMapper,
                                     IUserResponseMapper userResponseMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @Operation(
            summary = UserControllerConstants.OPERATION_SUMMARY,
            description = UserControllerConstants.OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = UserControllerConstants.RESPONSE_201_CODE,
                    description = UserControllerConstants.RESPONSE_201_MESSAGE),
            @ApiResponse(responseCode = UserControllerConstants.RESPONSE_400_CODE,
                    description = UserControllerConstants.RESPONSE_400_MESSAGE),
            @ApiResponse(responseCode = UserControllerConstants.RESPONSE_500_CODE,
                    description = UserControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(UserControllerConstants.CREATE_PATH)
    public ResponseEntity<UserResponse> createAuxBodegaUser(@RequestBody AddUserRequest addUserRequest) {
        var user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createAuxBodegaUser(user);
        var userResponse = userResponseMapper.toResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}