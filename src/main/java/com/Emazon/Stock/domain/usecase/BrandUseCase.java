package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.utilities.PagedResult;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.constants.BrandUseCaseConstants;

import java.util.List;

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

    @Override
    public PagedResult<Brand> getPagedBrands(Integer page, Integer size, boolean ascending) {
        return brandPersistencePort.getPagedBrands(page, size, ascending);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandPersistencePort.getAllBrands();
    }

    private void validateBrand(Brand brand) {
        if (brand == null) {
            throw new IllegalArgumentException(BrandUseCaseConstants.BRAND_NULL_EXCEPTION_MESSAGE);
        }
        if (brand.getNombre() == null || brand.getNombre().isEmpty()) {
            throw new IllegalArgumentException(BrandUseCaseConstants.BRAND_NAME_NULL_OR_EMPTY_MESSAGE);
        }
        if (brand.getNombre().length() > 50) {
            throw new IllegalArgumentException(BrandUseCaseConstants.BRAND_NAME_LENGTH_MESSAGE);
        }
        if (brand.getDescripcion() == null || brand.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException(BrandUseCaseConstants.BRAND_DESCRIPTION_NULL_OR_EMPTY_MESSAGE);
        }
        if (brand.getDescripcion().length() > 120) {
            throw new IllegalArgumentException(BrandUseCaseConstants.BRAND_DESCRIPTION_LENGTH_MESSAGE);
        }
    }

    private void checkIfNameIsUnique(String nombre, Long id) {
        boolean exists = brandPersistencePort.existsByName(nombre);

        if (exists) {
            throw new BrandAlreadyExistsDomainException(BrandUseCaseConstants.BRAND_ALREADY_EXISTS_MESSAGE);
        }
    }
}