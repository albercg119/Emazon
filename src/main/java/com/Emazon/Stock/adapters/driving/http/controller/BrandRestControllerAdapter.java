package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandResponseMapper;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.adapters.utilities.BrandControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(BrandControllerConstants.BRAND_BASE_ENDPOINT)
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = BrandControllerConstants.ADD_BRAND_SUMMARY)
    @ApiResponse(responseCode = BrandControllerConstants.CREATED_RESPONSE_CODE,
            description = BrandControllerConstants.BRAND_CREATED_DESCRIPTION)
    @PostMapping(BrandControllerConstants.BRAND_ADD_ENDPOINT)
    public ResponseEntity<String> addBrand(@Valid @RequestBody AddBrandRequest request) {
        brandServicePort.saveBrand(brandRequestMapper.addRequestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BrandControllerConstants.BRAND_CREATED_MESSAGE);
    }
}