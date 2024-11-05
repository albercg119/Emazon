package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.exception.BrandAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;

import java.util.List;

public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    public BrandAdapter(IBrandRepository brandRepository, IBrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.findByNombre(brand.getNombre()).isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }


    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByNombre(name).isPresent();
    }


}