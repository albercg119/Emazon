package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.ClientControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(ClientControllerConstants.BASE_PATH)
public class ClientController {

    private final IUserServicePort userServicePort;

    public ClientController(IUserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Operation(
            summary = ClientControllerConstants.ADD_TO_CART_SUMMARY,
            description = ClientControllerConstants.ADD_TO_CART_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_200_CODE,
                    description = ClientControllerConstants.RESPONSE_200_ADD_CART),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_403_CODE,
                    description = ClientControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_500_CODE,
                    description = ClientControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(ClientControllerConstants.CART_ADD_PATH)
    public ResponseEntity<?> addToCart(Authentication authentication) {
        if (!userServicePort.isClient(authentication.getName())) {
            return ResponseEntity.status(403).body(ClientControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(ClientControllerConstants.PRODUCT_ADDED_BODY);
    }

    @Operation(
            summary = ClientControllerConstants.REMOVE_FROM_CART_SUMMARY,
            description = ClientControllerConstants.REMOVE_FROM_CART_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_200_CODE,
                    description = ClientControllerConstants.RESPONSE_200_REMOVE_CART),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_403_CODE,
                    description = ClientControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_500_CODE,
                    description = ClientControllerConstants.RESPONSE_500_MESSAGE)
    })
    @DeleteMapping(ClientControllerConstants.CART_REMOVE_PATH)
    public ResponseEntity<?> removeFromCart(Authentication authentication) {
        if (!userServicePort.isClient(authentication.getName())) {
            return ResponseEntity.status(403).body(ClientControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(ClientControllerConstants.PRODUCT_REMOVED_BODY);
    }

    @Operation(
            summary = ClientControllerConstants.MAKE_PURCHASE_SUMMARY,
            description = ClientControllerConstants.MAKE_PURCHASE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_200_CODE,
                    description = ClientControllerConstants.RESPONSE_200_PURCHASE),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_403_CODE,
                    description = ClientControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = ClientControllerConstants.RESPONSE_500_CODE,
                    description = ClientControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(ClientControllerConstants.PURCHASE_PATH)
    public ResponseEntity<?> makePurchase(Authentication authentication) {
        if (!userServicePort.isClient(authentication.getName())) {
            return ResponseEntity.status(403).body(ClientControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(ClientControllerConstants.PURCHASE_COMPLETED_BODY);
    }
}