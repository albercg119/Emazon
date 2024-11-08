package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.InitialSetupControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(InitialSetupControllerConstants.BASE_PATH)
public class InitialSetupController {

    private final IUserServicePort userServicePort;

    public InitialSetupController(IUserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Operation(
            summary = InitialSetupControllerConstants.CREATE_ADMIN_SUMMARY,
            description = InitialSetupControllerConstants.CREATE_ADMIN_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_200_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_200_ADMIN),
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_400_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_400_EMAIL),
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_500_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(InitialSetupControllerConstants.ADMIN_PATH)
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        if (userServicePort.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(InitialSetupControllerConstants.EMAIL_IN_USE_BODY);
        }

        user.setRole(Role.ADMIN);
        userServicePort.createUser(user);
        return ResponseEntity.ok(InitialSetupControllerConstants.ADMIN_CREATED_BODY);
    }

    @Operation(
            summary = InitialSetupControllerConstants.CREATE_FIRST_ADMIN_SUMMARY,
            description = InitialSetupControllerConstants.CREATE_FIRST_ADMIN_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_200_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_200_FIRST_ADMIN),
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_400_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_400_FIRST_ADMIN),
            @ApiResponse(responseCode = InitialSetupControllerConstants.RESPONSE_500_CODE,
                    description = InitialSetupControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(InitialSetupControllerConstants.FIRST_ADMIN_PATH)
    public ResponseEntity<?> createFirstAdmin(@RequestBody User user) {
        if (userServicePort.existsAnyUser()) {
            return ResponseEntity.badRequest().body(InitialSetupControllerConstants.SETUP_COMPLETED_BODY);
        }

        user.setRole(Role.ADMIN);
        userServicePort.createUser(user);
        return ResponseEntity.ok(InitialSetupControllerConstants.FIRST_ADMIN_CREATED_BODY);
    }
}