package com.Emazon.Stock.domain.spi;

import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean existsByName(String name);
    boolean existsByNameExcludingId(String name, Long id);
    PagedResult<Brand> getPagedBrands(Integer page, Integer size, boolean ascending);
    List<Brand> getAllBrands();
}