package com.Emazon.Stock.adapters.driving.http.controller;


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


import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = "Añadir una nueva marca")
    @ApiResponse(responseCode = "201", description = "Marca creada con éxito")
    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@RequestBody AddBrandRequest request) {
        brandServicePort.saveBrand(brandRequestMapper.addRequestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las marcas con paginación")
    @ApiResponse(responseCode = "200", description = "Marcas encontradas")
    @GetMapping("/paged")
    public ResponseEntity<PagedResult<BrandResponse>> getPagedBrands(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "ascending", defaultValue = "true") boolean ascending) {
        PagedResult<BrandResponse> response = brandResponseMapper.toBrandResponsePagedResult(
                brandServicePort.getPagedBrands(page, size, ascending));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener todas las marcas sin paginación")
    @ApiResponse(responseCode = "200", description = "Marcas encontradas")
    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        return ResponseEntity.ok(brandResponseMapper.toBrandResponseList(brandServicePort.getAllBrands()));
    }
}