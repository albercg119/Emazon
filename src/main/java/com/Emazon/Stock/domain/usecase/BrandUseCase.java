package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
        checkIfNameIsUnique(brand.getNombre(), null);
        brandPersistencePort.saveBrand(brand);
    }



    private void validateBrand(Brand brand) {
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        if (brand.getNombre() == null || brand.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }
        if (brand.getNombre().length() > 50) {
            throw new IllegalArgumentException("Brand name cannot exceed 50 characters");
        }
        if (brand.getDescripcion() == null || brand.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("Brand description cannot be null or empty");
        }
        if (brand.getDescripcion().length() > 120) {
            throw new IllegalArgumentException("Brand description cannot exceed 120 characters");
        }
    }

    private void checkIfNameIsUnique(String nombre, Long id) {
        boolean exists;
        if (id == null) {
            exists = brandPersistencePort.existsByName(nombre);
        } else {
            exists = brandPersistencePort.existsByNameExcludingId(nombre, id);
        }

        if (exists) {
            throw new IllegalArgumentException("Brand name must be unique");
        }
    }
}