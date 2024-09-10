package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(summary = "Añadir una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada con éxito")
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las categorías con paginación")
    @ApiResponse(responseCode = "200", description = "Categorías encontradas")
    @GetMapping("/paged")
    public ResponseEntity<PagedResult<CategoryResponse>> getPagedCategories(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "ascending", defaultValue = "true") boolean ascending) {
        PagedResult<CategoryResponse> response = categoryResponseMapper.toCategoryResponsePagedResult(
                categoryServicePort.getPagedCategories(page, size, ascending));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories()));
    }

}