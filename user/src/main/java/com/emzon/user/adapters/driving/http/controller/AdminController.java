package com.emzon.user.adapters.driving.http.controller;

import com.emzon.user.adapters.utilities.AdminControllerConstants;
import com.emzon.user.domain.api.IUserServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(AdminControllerConstants.BASE_PATH)
public class AdminController {

    private final IUserServicePort userServicePort;

    public AdminController(IUserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Operation(
            summary = AdminControllerConstants.CREATE_CATEGORY_SUMMARY,
            description = AdminControllerConstants.CREATE_CATEGORY_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_200_CODE,
                    description = AdminControllerConstants.RESPONSE_200_CATEGORY),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_403_CODE,
                    description = AdminControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_500_CODE,
                    description = AdminControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(AdminControllerConstants.CATEGORY_PATH)
    public ResponseEntity<?> createCategory(Authentication authentication) {
        if (!userServicePort.isAdmin(authentication.getName())) {
            return ResponseEntity.status(403).body(AdminControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(AdminControllerConstants.CATEGORY_CREATED_BODY);
    }

    @Operation(
            summary = AdminControllerConstants.CREATE_BRAND_SUMMARY,
            description = AdminControllerConstants.CREATE_BRAND_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_200_CODE,
                    description = AdminControllerConstants.RESPONSE_200_BRAND),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_403_CODE,
                    description = AdminControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_500_CODE,
                    description = AdminControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(AdminControllerConstants.BRAND_PATH)
    public ResponseEntity<?> createBrand(Authentication authentication) {
        if (!userServicePort.isAdmin(authentication.getName())) {
            return ResponseEntity.status(403).body(AdminControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(AdminControllerConstants.BRAND_CREATED_BODY);
    }

    @Operation(
            summary = AdminControllerConstants.CREATE_ARTICLE_SUMMARY,
            description = AdminControllerConstants.CREATE_ARTICLE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_200_CODE,
                    description = AdminControllerConstants.RESPONSE_200_ARTICLE),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_403_CODE,
                    description = AdminControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_500_CODE,
                    description = AdminControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(AdminControllerConstants.ARTICLE_PATH)
    public ResponseEntity<?> createArticle(Authentication authentication) {
        if (!userServicePort.isAdmin(authentication.getName())) {
            return ResponseEntity.status(403).body(AdminControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(AdminControllerConstants.ARTICLE_CREATED_BODY);
    }

    @Operation(
            summary = AdminControllerConstants.CREATE_AUX_BODEGA_SUMMARY,
            description = AdminControllerConstants.CREATE_AUX_BODEGA_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_200_CODE,
                    description = AdminControllerConstants.RESPONSE_200_AUX_BODEGA),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_403_CODE,
                    description = AdminControllerConstants.RESPONSE_403_MESSAGE),
            @ApiResponse(responseCode = AdminControllerConstants.RESPONSE_500_CODE,
                    description = AdminControllerConstants.RESPONSE_500_MESSAGE)
    })
    @PostMapping(AdminControllerConstants.AUX_BODEGA_PATH)
    public ResponseEntity<?> createAuxBodega(Authentication authentication) {
        if (!userServicePort.isAdmin(authentication.getName())) {
            return ResponseEntity.status(403).body(AdminControllerConstants.ACCESS_DENIED_BODY);
        }
        return ResponseEntity.ok(AdminControllerConstants.AUX_BODEGA_CREATED_BODY);
    }
}