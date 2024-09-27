package com.Emazon.Stock.domain.spi;

import com.Emazon.Stock.domain.model.Brand;



public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean existsByName(String name);
}