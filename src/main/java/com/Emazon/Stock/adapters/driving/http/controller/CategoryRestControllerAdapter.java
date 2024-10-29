package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.utilities.CategoryControllerConstants;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryControllerConstants.CATEGORY_BASE_PATH)
@Tag(name = CategoryControllerConstants.CATEGORY_CONTROLLER_TAG,
        description = CategoryControllerConstants.CATEGORY_CONTROLLER_TAG_DESC)
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    public CategoryRestControllerAdapter(ICategoryServicePort categoryServicePort,
                                         ICategoryRequestMapper categoryRequestMapper,
                                         ICategoryResponseMapper categoryResponseMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @Operation(
            summary = CategoryControllerConstants.CATEGORY_CREATED_SUMMARY,
            description = CategoryControllerConstants.CATEGORY_OPERATION_DESCRIPTION
    )
    @ApiResponse(
            responseCode = CategoryControllerConstants.CATEGORY_SUCCESS_CODE,
            description = CategoryControllerConstants.CATEGORY_SUCCESS_RESPONSE
    )
    @ApiResponse(
            responseCode = CategoryControllerConstants.CATEGORY_BAD_REQUEST_CODE,
            description = CategoryControllerConstants.CATEGORY_BAD_REQUEST_RESPONSE
    )
    @ApiResponse(
            responseCode = CategoryControllerConstants.CATEGORY_CONFLICT_CODE,
            description = CategoryControllerConstants.CATEGORY_CONFLICT_RESPONSE
    )
    @PostMapping(CategoryControllerConstants.CATEGORY_POST_PATH)
    public ResponseEntity<String> addCategory(
            @Parameter(description = CategoryControllerConstants.CATEGORY_REQUEST_PARAM_DESC)
            @RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryControllerConstants.CATEGORY_CREATED_SUCCESSFULLY);
    }
}