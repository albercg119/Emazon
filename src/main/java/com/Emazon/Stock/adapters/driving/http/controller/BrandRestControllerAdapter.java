package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.utilities.BrandControllerConstants;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandResponseMapper;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Emazon.Stock.domain.utilities.PagedResult;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = BrandControllerConstants.BRAND_CREATED_SUMMARY)
    @ApiResponse(responseCode = BrandControllerConstants.BRAND_SUCCESS_CODE,
            description = BrandControllerConstants.BRAND_CREATED_SUCCESSFULLY)
    @PostMapping("/")
    public ResponseEntity<String> addBrand(@Valid @RequestBody  AddBrandRequest request) {
        brandServicePort.saveBrand(brandRequestMapper.addRequestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BrandControllerConstants.BRAND_CREATED_SUCCESSFULLY);
    }

    @Operation(summary = BrandControllerConstants.BRANDS_PAGED_SUMMARY)
    @ApiResponse(responseCode = BrandControllerConstants.BRANDS_FOUND_CODE,
            description = BrandControllerConstants.BRANDS_FOUND)
    @GetMapping("/paged")
    public ResponseEntity<PagedResult<BrandResponse>> getPagedBrands(
            @RequestParam(value = BrandControllerConstants.PARAM_PAGE,
                    defaultValue = BrandControllerConstants.DEFAULT_PAGE) Integer page,
            @RequestParam(value = BrandControllerConstants.PARAM_SIZE,
                    defaultValue = BrandControllerConstants.DEFAULT_SIZE) Integer size,
            @RequestParam(value = BrandControllerConstants.PARAM_SORT,
                    defaultValue = BrandControllerConstants.DEFAULT_SORT) String sort) {

        boolean ascending = sort.equalsIgnoreCase(BrandControllerConstants.SORT_ASCENDING);

        PagedResult<BrandResponse> response = brandResponseMapper.toBrandResponsePagedResult(
                brandServicePort.getPagedBrands(page, size, ascending));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        return ResponseEntity.ok(brandResponseMapper.toBrandResponseList(brandServicePort.getAllBrands()));
    }
}
