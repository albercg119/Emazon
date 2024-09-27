package com.Emazon.Stock.domain.api;

import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PagedResult<Brand> getPagedBrands(Integer page, Integer size, boolean ascending);
    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
}