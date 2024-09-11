package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.BrandAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


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
    public PagedResult<Brand> getPagedBrands(Integer page, Integer size, boolean ascending) {
        Sort sort = ascending ? Sort.by("nombre").ascending() : Sort.by("nombre").descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<BrandEntity> brandPage = brandRepository.findAll(pageRequest);

        if (brandPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Brand> brands = brandEntityMapper.toModelList(brandPage.getContent());
        return new PagedResult<>(brands, brandPage.getNumber(), brandPage.getSize(), brandPage.getTotalElements(), brandPage.getTotalPages());
    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            throw new NoDataFoundException();
        }
        return brandEntityMapper.toModelList(brands);
    }


    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByNombre(name).isPresent();
    }

    @Override
    public boolean existsByNameExcludingId(String nombre, Long id) {
        return brandRepository.existsByNombreExcludingId(nombre, id);
    }
}