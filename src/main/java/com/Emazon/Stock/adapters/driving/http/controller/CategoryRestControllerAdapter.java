package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.utilities.CategoryControllerConstants;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

@RestController
@RequestMapping("/category")
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

    @Operation(summary = CategoryControllerConstants.CATEGORY_CREATED_SUMMARY)
    @ApiResponse(responseCode = CategoryControllerConstants.CATEGORY_SUCCESS_CODE,
            description = CategoryControllerConstants.CATEGORY_CREATED_SUCCESSFULLY)
    @PostMapping("/")
    public ResponseEntity<String> addCategory(@RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryControllerConstants.CATEGORY_CREATED_SUCCESSFULLY);
    }

    @Operation(summary = CategoryControllerConstants.CATEGORIES_PAGED_SUMMARY)
    @ApiResponse(responseCode = CategoryControllerConstants.CATEGORIES_FOUND_CODE,
            description = CategoryControllerConstants.CATEGORIES_FOUND)
    @GetMapping("/paged")
    public ResponseEntity<PagedResult<CategoryResponse>> getPagedCategories(
            @RequestParam(value = CategoryControllerConstants.PARAM_PAGE,
                    defaultValue = CategoryControllerConstants.DEFAULT_PAGE) Integer page,
            @RequestParam(value = CategoryControllerConstants.PARAM_SIZE,
                    defaultValue = CategoryControllerConstants.DEFAULT_SIZE) Integer size,
            @RequestParam(value = CategoryControllerConstants.PARAM_SORT,
                    defaultValue = CategoryControllerConstants.DEFAULT_SORT) String sort) {
        boolean ascending = sort.equalsIgnoreCase(CategoryControllerConstants.SORT_ASCENDING);

        PagedResult<CategoryResponse> response = categoryResponseMapper.toCategoryResponsePagedResult(
                categoryServicePort.getPagedCategories(page, size, ascending));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = CategoryControllerConstants.CATEGORIES_UNPAGED_SUMMARY)
    @ApiResponse(responseCode = CategoryControllerConstants.CATEGORIES_FOUND_CODE,
            description = CategoryControllerConstants.CATEGORIES_FOUND)
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories()));
    }
}
