package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.AuxBodegaControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(AuxBodegaControllerConstants.BASE_PATH)
public class AuxBodegaController {

    private final IUserServicePort userServicePort;

    public AuxBodegaController(IUserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Operation(
            summary = AuxBodegaControllerConstants.ADD_SUPPLY_SUMMARY,
            description = AuxBodegaControllerConstants.ADD_SUPPLY_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AuxBodegaControllerConstants.RESPONSE_200_CODE,
                    description = AuxBodegaControllerConstants.RESPONSE_200_MESSAGE),
            @ApiResponse(responseCode = AuxBodegaControllerConstants.RESPONSE_403_CODE,
                    description = AuxBodegaControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = AuxBodegaControllerConstants.RESPONSE_500_CODE,
                    description = AuxBodegaControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(AuxBodegaControllerConstants.SUPPLY_PATH)
    public ResponseEntity<?> addSupply(Authentication authentication) {
        if (!userServicePort.isAuxBodega(authentication.getName())) {
            return ResponseEntity.status(403).body(AuxBodegaControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(AuxBodegaControllerConstants.SUPPLY_ADDED_BODY);
    }
}